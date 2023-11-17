package bg.softuni.exam.service;

import bg.softuni.exam.entity.Category;
import bg.softuni.exam.entity.Product;
import bg.softuni.exam.entity.enums.CategoryNameEnum;
import bg.softuni.exam.models.ProductServiceModel;


import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    List<Category> findAllByCategoryName(CategoryNameEnum categoryNameEnum);

    List<Product> getAllProducts();


}
