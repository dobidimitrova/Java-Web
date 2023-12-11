package bg.softuni.exam.service;

import bg.softuni.exam.entity.Category;
import bg.softuni.exam.entity.enums.CategoryNameEnum;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void initCategories();

    Category findByCategoryName(CategoryNameEnum categoryNameEnum);

    List<Category> getAllCategories();

    Category getCategoryByName(String categoryName);


}
