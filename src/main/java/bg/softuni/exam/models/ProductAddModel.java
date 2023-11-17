package bg.softuni.exam.models;

import bg.softuni.exam.entity.enums.CategoryNameEnum;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductAddModel {

    @Length(min = 1,message = "more than 1")
    @NotEmpty
    private String name;

    @Length(min = 5,message = "more than 5")
    private String description;

    @DecimalMin(value = "0",message = "can not be negative")
    @Positive
    private BigDecimal price;

    @NotNull(message = "can not be null")
    private String category;


    public ProductAddModel() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
