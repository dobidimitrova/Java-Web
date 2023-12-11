package bg.softuni.exam.web;

import bg.softuni.exam.entity.Category;
import bg.softuni.exam.models.ProductAddModel;
import bg.softuni.exam.models.ProductServiceModel;
import bg.softuni.exam.service.CategoryService;
import bg.softuni.exam.service.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, CategoryService categoryService, ModelMapper modelMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model) {

        if (!model.containsAttribute("productAddModel")) {
            model.addAttribute("productAddModel", new ProductAddModel());
            return "product-add";
        }
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("categories", categories);

        // Add a new product and an empty model for form binding
        model.addAttribute("product", new ProductAddModel());
        return  "product-add";
    }

    @ModelAttribute("productAddModel")
    public ProductAddModel productAddModel() {
        return new ProductAddModel();
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ProductAddModel productAddModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddModel", productAddModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddModel", bindingResult);
            return "product-add";
        }


        String categoryName = productAddModel.getCategory();
        if(categoryName.equals("1")){
            categoryName="Foundation";
        } else if(categoryName.equals("2")){
            categoryName="Powder";
        } else if(categoryName.equals("3")){
            categoryName="Bronzer";
        } else if(categoryName.equals("4")){
            categoryName="Mascara";
        } else if(categoryName.equals("5")){
            categoryName="Eyebrows";
        } else if(categoryName.equals("6")){
            categoryName="Brushes";
        }

        Category category = categoryService.getCategoryByName(categoryName);

        ProductServiceModel productServiceModel = modelMapper.map(productAddModel, ProductServiceModel.class);
        productServiceModel.setCategory(category.getName());

        productService.addProduct(productServiceModel);

        return "redirect:/";
    }

}
