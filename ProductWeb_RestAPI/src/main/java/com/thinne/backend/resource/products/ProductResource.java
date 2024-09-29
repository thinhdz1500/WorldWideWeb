package com.thinne.backend.resource.products;

import com.thinne.backend.business.ProductLocal;
import com.thinne.backend.data.entities.Product;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;


import java.util.List;

@Path("/products")
public class ProductResource {
    @EJB
    private ProductLocal productLocal;

    @GET
    public Response getProducts() {
        return Response.ok(productLocal.getProducts()).build();
    }
    @GET
    @Path("/{id}")
    public Response getProduct( @PathParam("id") int id) {
        return Response.ok(productLocal.getProduct(id)).build();
    }

    @POST
    public Response addProduct(Product product) {
        productLocal.add(product);
        return Response.ok(product).build();
    }
}
