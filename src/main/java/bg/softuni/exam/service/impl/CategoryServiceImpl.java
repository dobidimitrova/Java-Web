package bg.softuni.exam.service.impl;


import bg.softuni.exam.entity.Category;
import bg.softuni.exam.entity.enums.CategoryNameEnum;
import bg.softuni.exam.repository.CategoryRepository;
import bg.softuni.exam.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if(categoryRepository.count()!=0){
            return;
        }
            Arrays.stream(CategoryNameEnum.values())
                    .forEach(categoryNameEnum -> {
                        Category category = new Category();
                        category.setName(categoryNameEnum);
                        categoryRepository.save(category);
                    });

    }

    @Override
    public Category findByCategoryName(CategoryNameEnum categoryNameEnum) {
        return categoryRepository.findByName(categoryNameEnum).orElse(null);

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByName(CategoryNameEnum.valueOf(categoryName))
                .orElseThrow(() -> new RuntimeException("Category not found: " + categoryName));
    }

}
