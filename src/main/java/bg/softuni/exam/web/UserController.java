package bg.softuni.exam.web;


import bg.softuni.exam.entity.User;
import bg.softuni.exam.models.UserLoginModel;
import bg.softuni.exam.models.UserRegisterModel;
import bg.softuni.exam.models.UserServiceModel;
import bg.softuni.exam.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("users/register")
    public String RegisterPage(Model model){
        if(!model.containsAttribute("userRegisterModel")) {
            model.addAttribute("userRegisterModel",new UserRegisterModel());
        }
        return "register";
    }

    @PostMapping("users/register")
    public String regConfirm(@Valid UserRegisterModel userRegisterModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() || !userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterModel",userRegisterModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterModel",bindingResult);
           return "redirect:register";
        }


        //save in db
        userService.registerModel(modelMapper.map(userRegisterModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/users/login")
    public String loginPage(Model model){
        if(!model.containsAttribute("userLoginModel")){
            model.addAttribute("userLoginModel",new UserLoginModel());
            model.addAttribute("notFound",false);
        }
        return "login";
    }


    @PostMapping("/users/login")
    public String loginConfirm(@Valid UserLoginModel userLoginModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession){

        if(bindingResult.hasErrors())
        {
            redirectAttributes.addFlashAttribute("userLoginModel",userLoginModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginModel",bindingResult);
            return "redirect:login";
        }

        UserServiceModel userServiceModel = userService.findByUsernameAndPassword(userLoginModel.getUsername(),userLoginModel.getPassword());

        if(userServiceModel==null){
            redirectAttributes.addFlashAttribute("userLoginModel",userLoginModel);
            redirectAttributes.addFlashAttribute("notFound",true);
            return  "redirect:login";
        }

        //log user

        httpSession.setAttribute("user",userServiceModel);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }


//    @GetMapping("/users/dashboard")
//    public String dashboard(HttpSession httpSession,Model model){
//        Object loggedInUser =  httpSession.getAttribute("user");
//
//        // Pass the user information to the HTML page
//        model.addAttribute("loggedInUser", loggedInUser);
//        return "dashboard";
//    }


}
