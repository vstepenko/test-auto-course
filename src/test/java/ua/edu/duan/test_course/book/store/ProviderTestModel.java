package ua.edu.duan.test_course.book.store;

import ua.edu.duan.test_course.book.store.state.BaseSateExecutor;

public class ProviderTestModel {

    public ProviderTestModel(OrderState orderState, Class<? extends BaseSateExecutor> expectedClass) {
        this.orderState = orderState;
        this.expectedClass = expectedClass;
    }

    private OrderState orderState;
    private Class<? extends BaseSateExecutor> expectedClass;

    public OrderState getOrderState() {
        return orderState;
    }

    public Class<? extends BaseSateExecutor> getExpectedClass() {
        return expectedClass;
    }
}
