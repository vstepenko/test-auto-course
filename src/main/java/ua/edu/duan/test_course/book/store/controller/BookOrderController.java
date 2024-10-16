package ua.edu.duan.test_course.book.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.duan.test_course.book.store.OrderState;
import ua.edu.duan.test_course.book.store.service.BookOrderService;
import ua.edu.duan.test_course.book.store.service.ProcessingOrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book-store")
public class BookOrderController {

    private final BookOrderService bookOrderService;

    private final ProcessingOrderService processingOrderService;
    @PostMapping(path = "/order")
    public OrderState createOrder(@RequestParam long itemId) {
        return bookOrderService.createOrder(itemId);
    }


    @PostMapping(path = "/process-order")
    public OrderState process(@RequestParam long orderId) {
        return processingOrderService.process(orderId);
    }
}
