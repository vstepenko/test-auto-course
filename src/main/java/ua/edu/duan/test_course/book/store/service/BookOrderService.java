package ua.edu.duan.test_course.book.store.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.duan.test_course.book.store.BookOrderStateProvider;
import ua.edu.duan.test_course.book.store.OrderState;
import ua.edu.duan.test_course.book.store.entity.BookOrderEntity;
import ua.edu.duan.test_course.book.store.repository.BookOrderRepository;
import ua.edu.duan.test_course.book.store.repository.OrderCountRepository;
import ua.edu.duan.test_course.book.store.state.BaseSateExecutor;

import java.lang.annotation.Repeatable;

@Service
@RequiredArgsConstructor
public class BookOrderService {

    private final BookOrderRepository bookOrderRepository;
    private final BookOrderStateProvider bookOrderStateProvider;
    private final OrderCountRepository orderCountRepository;


    @PostConstruct
    private void init() {
        bookOrderRepository.deleteAllInBatch();
        orderCountRepository.findById(1L).ifPresent(
                orderCountEntity -> {
                    orderCountEntity.setCount(0);
                    orderCountRepository.save(orderCountEntity);
                }
        );
    }

    @Transactional
    public OrderState createOrder(long bookId) {

        BookOrderEntity order = new BookOrderEntity();
        order.setItemId(bookId);
        order.setState(OrderState.NEW);

        BaseSateExecutor executor = bookOrderStateProvider.getState(order.getState());
        executor.executeOrder(order);

        orderCountRepository.findByIdWithLock(1L).ifPresent(
                orderCountEntity -> orderCountEntity.setCount(orderCountEntity.getCount() + 1));

        bookOrderRepository.save(order);
        return order.getState();
    }
}
