package com.kramar.Market.rest.controller;

import com.kramar.Market.orders.OrderService;
import com.kramar.Market.rest.dto.order.OrderFullInfoUIAdapter;
import com.kramar.Market.rest.dto.test.Greeting;
import com.kramar.Market.users.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final String ordersViewName = "ordersList";
    private final String usersViewName = "usersList";
    private final String orderDetailsView = "orderDetailsView";

    private final OrderService orderService;
    private final UserService userService;

    public AdminController(OrderService orderService,
                           UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping(value = "/orders")
    public ModelAndView getOrders() {
        ModelAndView context = new ModelAndView(ordersViewName);
        context.addObject("orders", orderService.getOrders());
        return context;
    }

    @GetMapping(value = "/users")
    public ModelAndView getUsers() {
        ModelAndView context = new ModelAndView(usersViewName);
        context.addObject("users", orderService.getOrders());
        return context;
    }

    @GetMapping(value = "/getOrderInfo/{id}")
    public ModelAndView getOrder(@PathVariable Long id) {
        ModelAndView context = new ModelAndView(orderDetailsView);
        context.addObject("order", orderService.getOrderFullInfo(id));
        return context;
    }

    @GetMapping(value = "/deleteOrder/{id}")
    public RedirectView deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new RedirectView("/admin/orders");
    }

    @PostMapping("/changeOrderInfo/{id}")
    public RedirectView changeOrder(@PathVariable Long id, @ModelAttribute OrderFullInfoUIAdapter adapter) {
        orderService.updateOrder(id, adapter);
        return new RedirectView("/admin/orders");
    }
}