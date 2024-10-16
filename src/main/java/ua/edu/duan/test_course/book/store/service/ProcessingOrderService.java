package ua.edu.duan.test_course.book.store.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.duan.test_course.book.store.BookOrderStateProvider;
import ua.edu.duan.test_course.book.store.OrderState;
import ua.edu.duan.test_course.book.store.repository.BookOrderRepository;
import ua.edu.duan.test_course.book.store.state.BaseSateExecutor;

@RequiredArgsConstructor
@Service
public class ProcessingOrderService {


    private final BookOrderRepository bookOrderRepository;
    private final BookOrderStateProvider bookOrderStateProvider;

    @Transactional
    public OrderState process(long orderId) {

        return bookOrderRepository.findById(orderId)
                .map(order -> {
                    BaseSateExecutor baseSateExecutor = bookOrderStateProvider.getState(order.getState());
                    return baseSateExecutor.executeOrder(order);
                })
                .orElseThrow(() -> new RuntimeException("Order not found order Id" + orderId));
    }
}
