package bg.softuni.exam.web;

import bg.softuni.exam.entity.Product;
import bg.softuni.exam.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/")
    public String index(HttpSession httpSession, Model model){
        if(httpSession.getAttribute("user")==null){
            return "index";
        }
          else {
            List<Product> products = productService.getAllProducts();
            model.addAttribute("products", products);
           return "home";
        }
    }



//    @GetMapping("/")
//    public ModelAndView indexPage(HttpSession httpSession, ModelAndView modelAndView){
//
//        if(httpSession.getAttribute("user") == null){
//            modelAndView.setViewName("index");
//        }else {
//            modelAndView.setViewName("home");
//        }
//
//        return modelAndView;
//    }



}
