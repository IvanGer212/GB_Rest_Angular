package com.geekbrains.ru.gb_rest_angular.core.controller;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.core.converter.OrderConverter;
import com.geekbrains.ru.gb_rest_angular.core.domain.User;
import com.geekbrains.ru.gb_rest_angular.core.dto.OrderDtoResponse;
import com.geekbrains.ru.gb_rest_angular.core.dto.OrderItemDto;
import com.geekbrains.ru.gb_rest_angular.core.dto.PageDto;
import com.geekbrains.ru.gb_rest_angular.core.service.OrderService;
import com.geekbrains.ru.gb_rest_angular.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/app/api/v1/order")
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder (Principal principal){
        User user = userService.findUserByEmail(principal.getName()).get();
        orderService.createOrder(user);
    }

    @GetMapping
    public PageDto<OrderDtoResponse> getOrders(@RequestParam (name="p", defaultValue = "1") Integer page, Principal principal){
        if (page<1){
            page = 1;
        }
        PageDto<OrderDtoResponse> out = new PageDto<>();
        Page<OrderDtoResponse> map;
        User user = userService.findUserByEmail(principal.getName()).get();
        boolean role_admin = user.getRoles().stream().map(role -> role.getName()).anyMatch(c -> c.equals("ROLE_ADMIN"));
        if (role_admin) {
            map = orderService.getAllOrders(page).map(orderConverter::entityToDtoResponse);
        }
        else {
            map = orderService.getOrders(page, principal.getName()).map(orderConverter::entityToDtoResponse);
        }
        out.setPage(map.getNumber());
        out.setItems(map.getContent());
        out.setTotalPages(map.getTotalPages());
        return out;
    }

    @GetMapping("/{id}")
    public List<OrderItemDto> getOrderItemsById(@PathVariable Long id){
        return orderService.findItemsByOrderId(id);
    }

}
