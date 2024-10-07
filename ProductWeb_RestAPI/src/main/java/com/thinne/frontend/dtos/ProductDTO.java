package com.thinne.frontend.dtos;

import java.util.Objects;

//@Entity(name="productDTO")
//@Table(name = "product")
//@NamedQueries({
//        @NamedQuery(name = "Product.findAll", query = "select p from Product p"),
//        @NamedQuery(name = "Product.findById", query = "select p from Product p where p.id = :id")
//})
public class ProductDTO {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "product_id", nullable = false)
//    private Long id;
//
//    @Size(max = 250)
//    @NotNull
//    @Column(name = "name", nullable = false, length = 250)
//    private String name;
//
//    @Size(max = 250)
//    @Column(name = "description", length = 250)
//    private String description;
//
//    @Size(max = 250)
//    @Column(name = "img_path", length = 250)
//    private String imgPath;
//    public Product(){
//
//    }
//    public Product(String name, String description, String imgPath) {
//        this.name = name;
//        this.description = description;
//        this.imgPath = imgPath;
//    }
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getImgPath() {
//        return imgPath;
//    }
//
//    public void setImgPath(String imgPath) {
//        this.imgPath = imgPath;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return Objects.equals(id, product.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(id);
//    }
//
//    @Override
//    public String toString() {
//        return "{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", imgPath='" + imgPath + '\'' +
//                '}';
//    }
    private int id;
    private String name;
    private String description;
    private String imgPath;
    private double price;

    public ProductDTO() {
    }

    public ProductDTO(String name, String description, String imgPath, double price) {
        this.name = name;
        this.description = description;
        this.imgPath = imgPath;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO productDTO = (ProductDTO) o;
        return id == productDTO.id && Double.compare(price, productDTO.price) == 0 && Objects.equals(name, productDTO.name) && Objects.equals(description, productDTO.description) && Objects.equals(imgPath, productDTO.imgPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, imgPath, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}