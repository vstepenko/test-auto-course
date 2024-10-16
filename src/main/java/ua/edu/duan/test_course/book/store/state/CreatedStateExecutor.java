package ua.edu.duan.test_course.book.store.state;

import org.springframework.stereotype.Component;
import ua.edu.duan.test_course.book.store.OrderState;
import ua.edu.duan.test_course.book.store.entity.BookOrderEntity;

@Component
public class CreatedStateExecutor implements BaseSateExecutor{
    @Override
    public OrderState getOrderState() {
        return OrderState.CREATED;
    }

    @Override
    public OrderState executeOrder(BookOrderEntity order) {
        //Some logic for this state
        //for example send email to customer
        order.setState(OrderState.PROCESSING);
        return order.getState();
    }
}
