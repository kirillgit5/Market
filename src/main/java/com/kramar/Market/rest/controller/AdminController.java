package com.kramar.Market.rest.controller;

import com.kramar.Market.goods.CakeService;
import com.kramar.Market.orders.OrderService;
import com.kramar.Market.rest.dto.CakeFullInfo;
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
    private final String cakesView = "cakeList";
    private final String createCakeView = "createCake";

    private final OrderService orderService;
    private final UserService userService;
    private final CakeService cakeService;

    public AdminController(OrderService orderService,
                           UserService userService,
                           CakeService cakeService) {
        this.orderService = orderService;
        this.userService = userService;
        this.cakeService = cakeService;
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
        System.out.println(id);
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

    @GetMapping(value = "/cakes")
    public ModelAndView getCakes() {
        ModelAndView context = new ModelAndView(cakesView);
        context.addObject("cakes", cakeService.getCakes());
        return context;
    }

    @GetMapping(value = "/createCake")
    public ModelAndView createCake() {
        ModelAndView contex = new ModelAndView(createCakeView);
        contex.addObject("cake", new CakeFullInfo());
        return contex;
    }

    @PostMapping(value = "/createCake")
    public RedirectView createCake(CakeFullInfo cake) {
        cakeService.addCake(cake);
        return new RedirectView("/admin/cakes");
    }
}