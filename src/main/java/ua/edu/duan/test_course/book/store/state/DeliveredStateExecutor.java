package ua.edu.duan.test_course.book.store.state;

import org.springframework.stereotype.Component;
import ua.edu.duan.test_course.book.store.OrderState;
import ua.edu.duan.test_course.book.store.entity.BookOrderEntity;

@Component
public class DeliveredStateExecutor implements BaseSateExecutor{
    @Override
    public OrderState getOrderState() {
        return OrderState.DELIVERED;
    }

    @Override
    public OrderState executeOrder(BookOrderEntity order) {
        //Do some logic for example create invoice for delivery
        order.setState(OrderState.COMPLETED);
        return order.getState();
    }
}
