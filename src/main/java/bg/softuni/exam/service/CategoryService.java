package bg.softuni.exam.service;

import bg.softuni.exam.entity.Category;
import bg.softuni.exam.entity.enums.CategoryNameEnum;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface CategoryService {
    void initCategories();

    Category findByCategoryName(CategoryNameEnum categoryNameEnum);

    List<Category> getAllCategories();
}
