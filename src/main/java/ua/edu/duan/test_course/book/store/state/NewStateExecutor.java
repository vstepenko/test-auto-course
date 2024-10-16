package ua.edu.duan.test_course.book.store.state;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.edu.duan.test_course.book.store.OrderState;
import ua.edu.duan.test_course.book.store.entity.BookOrderEntity;

import ua.edu.duan.test_course.book.store.repository.WarehouseRepository;


@RequiredArgsConstructor
@Component
public class NewStateExecutor implements BaseSateExecutor {

    private final WarehouseRepository warehouseRepository;

    @Override
    public OrderState getOrderState() {
        return OrderState.NEW;
    }

    @Override
    public OrderState executeOrder(BookOrderEntity order) {
        warehouseRepository.findById(order.getItemId()).ifPresentOrElse(
                warehouseEntity -> {
                    if (warehouseEntity.getCount() > 0) {
                        order.setState(OrderState.CREATED);
                    } else {
                        order.setState(OrderState.CANCELLED);
                    }
                },
                () -> {
                    throw new RuntimeException("Item with id not found - id: " + order.getItemId());
                }
        );
        return order.getState();
    }
}
