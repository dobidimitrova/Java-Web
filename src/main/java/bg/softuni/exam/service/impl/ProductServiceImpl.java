package bg.softuni.exam.service.impl;

import bg.softuni.exam.entity.Category;
import bg.softuni.exam.entity.Product;
import bg.softuni.exam.entity.enums.CategoryNameEnum;
import bg.softuni.exam.models.ProductServiceModel;
import bg.softuni.exam.repository.CategoryRepository;
import bg.softuni.exam.repository.ProductRepository;
import bg.softuni.exam.service.CategoryService;
import bg.softuni.exam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {

        Product product = modelMapper.map(productServiceModel,Product.class);
        product.setCategory(categoryService
                .findByCategoryName(productServiceModel.getCategory()));
        productRepository.save(product);
    }

    @Override
    public List<Category> findAllByCategoryName(CategoryNameEnum categoryNameEnum) {

        List<Category> list = new ArrayList<>();
        list.add(categoryService.findByCategoryName(categoryNameEnum));
        return list;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }




}
