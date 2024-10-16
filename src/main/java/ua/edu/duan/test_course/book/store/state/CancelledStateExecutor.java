package ua.edu.duan.test_course.book.store.state;

import org.springframework.stereotype.Component;
import ua.edu.duan.test_course.book.store.OrderState;
import ua.edu.duan.test_course.book.store.entity.BookOrderEntity;

@Component
public class CancelledStateExecutor implements BaseSateExecutor{

    @Override
    public OrderState getOrderState() {
        return OrderState.CANCELLED;
    }

    @Override
    public OrderState executeOrder(BookOrderEntity order) {
        throw new RuntimeException("Order is already in final state");
    }
}
