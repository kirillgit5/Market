package com.kramar.Market.rest.controller;

import com.kramar.Market.goods.CakeEntity;
import com.kramar.Market.goods.CakeRepository;
import com.kramar.Market.goods.CakeService;
import com.kramar.Market.orders.OrderService;
import com.kramar.Market.rest.dto.CakeFullInfo;
import com.kramar.Market.rest.dto.order.OrderFullInfoUIAdapter;
import com.kramar.Market.rest.dto.test.Greeting;
import com.kramar.Market.users.UserService;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@Slf4j
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
    private final CakeRepository cakeRepo;
    private final PlatformTransactionManager platformTransactionManager;

    public AdminController(OrderService orderService,
                           UserService userService,
                           CakeService cakeService,
                           CakeRepository cakeRepo,
                           PlatformTransactionManager platformTransactionManager
                           ) {
        this.orderService = orderService;
        this.userService = userService;
        this.cakeService = cakeService;
        this.cakeRepo = cakeRepo;
        this.platformTransactionManager = platformTransactionManager;
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

    @Transactional
    @GetMapping(value = "/deleteOrder/{id}")
    public RedirectView deleteOrder(@PathVariable Long id) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                @SneakyThrows
                protected void doInTransactionWithoutResult(@NonNull TransactionStatus status) {
                    Optional<CakeEntity> cake = cakeRepo.findFirstBy();
                    log.warn("{}", cake);
                    Thread.sleep(10000);

                }
            });

        });

        Thread thread2 = new Thread(() -> {
            TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @SneakyThrows
                @Override
                protected void doInTransactionWithoutResult(@NonNull TransactionStatus status) {
                    log.warn("Thread2");
                    Optional<CakeEntity> cake = cakeRepo.findFirstBy();
                    log.warn("{}", cake);
                    Thread.sleep(10000);
                }
            });
        });

        thread1.start();
        thread2.start();

        return new RedirectView("/admin/orders");
    }

    @PostMapping("/changeOrderInfo/{id}")
    public RedirectView changeOrder(@PathVariable Long id, @ModelAttribute OrderFullInfoUIAdapter adapter) {
//        orderService.updateOrder(id, adapter);
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