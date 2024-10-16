package ua.edu.duan.test_course.book.store.executor;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ua.edu.duan.test_course.book.store.OrderState;
import ua.edu.duan.test_course.book.store.entity.BookOrderEntity;
import ua.edu.duan.test_course.book.store.entity.WarehouseEntity;
import ua.edu.duan.test_course.book.store.repository.WarehouseRepository;
import ua.edu.duan.test_course.book.store.state.NewStateExecutor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class NewStateExecutorTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @InjectMocks
    private NewStateExecutor newStateExecutor;

    @Test
    public void testStateExecuteReturnNew(){
        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setCount(10);

        Mockito.when(warehouseRepository.findById(any())).thenReturn(Optional.of(warehouseEntity));


        BookOrderEntity bookOrderEntity = new BookOrderEntity();
        OrderState orderState = newStateExecutor.executeOrder(bookOrderEntity);

        assertEquals(OrderState.CREATED, orderState);
        assertEquals(OrderState.CREATED, bookOrderEntity.getState());
        Mockito.verify(warehouseRepository, Mockito.times(1)).findById(any());

    }


    @Test
    public void testStateExecuteReturnCanceled(){
        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setCount(0);

        Mockito.when(warehouseRepository.findById(any())).thenReturn(Optional.of(warehouseEntity));


        BookOrderEntity bookOrderEntity = new BookOrderEntity();
        OrderState orderState = newStateExecutor.executeOrder(bookOrderEntity);

        assertEquals(OrderState.CANCELLED, orderState);
        assertEquals(OrderState.CANCELLED, bookOrderEntity.getState());
        Mockito.verify(warehouseRepository, Mockito.times(1)).findById(any());

    }
}
