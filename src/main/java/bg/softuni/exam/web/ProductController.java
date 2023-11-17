package bg.softuni.exam.web;

import bg.softuni.exam.entity.Category;
import bg.softuni.exam.entity.Product;
import bg.softuni.exam.entity.ProductImage;
import bg.softuni.exam.models.ProductAddModel;

import bg.softuni.exam.models.ProductServiceModel;
import bg.softuni.exam.service.CategoryService;
import bg.softuni.exam.service.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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
                             RedirectAttributes redirectAttributes,
                             @ModelAttribute Product product,
                             @RequestParam("file") MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddModel", productAddModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddModel", bindingResult);
            return "product-add";
        }

        ProductImage productImage = new ProductImage();
        productImage.setImageData(file.getBytes());
        productImage.setImageFileName(file.getOriginalFilename());
        productImage.setImageMimeType(file.getContentType());
        productImage.setProduct(product);

        productService.addProduct(modelMapper.map(productAddModel, ProductServiceModel.class));

        return "redirect:/";


    }

}
