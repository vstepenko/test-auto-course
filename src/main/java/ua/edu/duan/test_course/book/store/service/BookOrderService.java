package ua.edu.duan.test_course.book.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.duan.test_course.book.store.BookOrderStateProvider;
import ua.edu.duan.test_course.book.store.OrderState;
import ua.edu.duan.test_course.book.store.entity.BookOrderEntity;
import ua.edu.duan.test_course.book.store.repository.BookOrderRepository;
import ua.edu.duan.test_course.book.store.state.BaseSateExecutor;

@Service
@RequiredArgsConstructor
public class BookOrderService {

    private final BookOrderRepository bookOrderRepository;
    private final BookOrderStateProvider bookOrderStateProvider;

    public OrderState createOrder(long bookId) {

        BookOrderEntity order = new BookOrderEntity();
        order.setItemId(bookId);
        order.setState(OrderState.NEW);

        BaseSateExecutor executor = bookOrderStateProvider.getState(order.getState());
        executor.executeOrder(order);

        bookOrderRepository.save(order);

        return order.getState();
    }
}
