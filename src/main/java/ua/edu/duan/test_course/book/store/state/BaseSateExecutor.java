package ua.edu.duan.test_course.book.store.state;

import ua.edu.duan.test_course.book.store.OrderState;
import ua.edu.duan.test_course.book.store.entity.BookOrderEntity;

public interface BaseSateExecutor {

    OrderState getOrderState();

    OrderState executeOrder(BookOrderEntity order);
}
