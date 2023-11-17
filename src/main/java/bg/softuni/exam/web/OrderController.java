package bg.softuni.exam.web;

import bg.softuni.exam.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    private final ModelMapper modelMapper;
    private final OrderService orderService;

    public OrderController(ModelMapper modelMapper, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String order(){
        return "order";
    }


}
