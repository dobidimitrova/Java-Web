package bg.softuni.exam.service.impl;

import bg.softuni.exam.repository.OrderRepository;
import bg.softuni.exam.service.OrderService;
import bg.softuni.exam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(ModelMapper modelMapper, ProductService productService, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }


}
