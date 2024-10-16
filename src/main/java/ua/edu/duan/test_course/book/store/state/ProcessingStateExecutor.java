package ua.edu.duan.test_course.book.store.state;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.edu.duan.test_course.book.store.OrderState;
import ua.edu.duan.test_course.book.store.entity.BookOrderEntity;
import ua.edu.duan.test_course.book.store.repository.WarehouseRepository;

@RequiredArgsConstructor
@Component
public class ProcessingStateExecutor  implements BaseSateExecutor{


    private final WarehouseRepository warehouseRepository;

    @Override
    public OrderState getOrderState() {
        return OrderState.PROCESSING;
    }

    @Override
    public OrderState executeOrder(BookOrderEntity order) {
        //do some logic
        warehouseRepository.findById(order.getItemId()).ifPresent(
                warehouseEntity -> warehouseEntity.setCount(warehouseEntity.getCount() - 1));
        order.setState(OrderState.DELIVERED);
        return order.getState();
    }
}
