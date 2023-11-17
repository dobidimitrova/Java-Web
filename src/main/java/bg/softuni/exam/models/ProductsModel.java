package bg.softuni.exam.models;

import bg.softuni.exam.entity.enums.CategoryNameEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductsModel {
    @Size(min = 3,max = 20,message = "error")
    @NotBlank(message = "is empty")
    private String name;

    @Size(min = 5,message = "is little")
    private String description;


    @DecimalMin(value = "0",message = "can not be negative")
    private BigDecimal price;

    @NotEmpty
    private CategoryNameEnum category;

    public ProductsModel() {
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


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnum category) {
        this.category = category;
    }
}
