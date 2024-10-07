package com.thinne.backend.resource.products;

import com.google.gson.Gson;
import com.thinne.backend.business.ProductLocal;
import com.thinne.backend.business.ProductPriceLocal;
import com.thinne.backend.data.entities.Product;
import com.thinne.backend.data.entities.ProductPrice;
import com.thinne.backend.dtos.ProductDTO;

import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.core.MediaType;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;


@Path("/products")
public class ProductResource {
    @EJB
    private ProductLocal productLocal;
    @EJB
    private ProductPriceLocal productPriceLocal;
    @GET
    public Response getProducts() {
        List<Product> products = productLocal.getProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(productPriceLocal.getProductPriceByProductId(product.getId()).getValue());
            productDTOs.add(productDTO);
        }
        return Response.ok(productDTOs).build();
    }
    @GET
    @Path("/{id}")
    public Response getProduct( @PathParam("id") int id) {
        return Response.ok(productLocal.getProduct(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(ProductDTO productDTO) {
        try {
            Product product = new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getImgPath());
            ProductPrice price = new ProductPrice();
            price.setValue(productDTO.getPrice());
            price.setProduct(product);
            price.setState((byte) 1);

            price.setApplyDate(LocalDate.now());
            productLocal.add(product); // Gọi phương thức add mà không cần trả về
            productPriceLocal.add(price);
        }
        catch (Exception e) {
            System.out.println("Error in addProduct"+ e);
//            e.printStackTrace();
        }
        return Response.status(Response.Status.CREATED).build(); // Trả về phản hồi 201 (Created)
    }
    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id) {
        try {
            // Kiểm tra xem sản phẩm có tồn tại không
            Product product = productLocal.getProduct(id);
            if (product != null) {
                productPriceLocal.delete(productPriceLocal.getProductPriceByProductId(product.getId()));
                productLocal.delete(product); // Gọi phương thức xóa
                return Response.noContent().build(); // Trả về phản hồi 204 No Content
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Sản phẩm không tìm thấy").build(); // Trả về 404 nếu không tìm thấy sản phẩm
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Có lỗi xảy ra: " + e.getMessage()).build(); // Trả về 500 nếu có lỗi
        }
    }
}
