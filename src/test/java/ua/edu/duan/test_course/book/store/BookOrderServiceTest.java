package ua.edu.duan.test_course.book.store;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;
import ua.edu.duan.test_course.book.store.entity.BookOrderEntity;
import ua.edu.duan.test_course.book.store.repository.BookOrderRepository;
import ua.edu.duan.test_course.book.store.service.BookOrderService;
import ua.edu.duan.test_course.book.store.state.NewStateExecutor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookOrderServiceTest {

    @Mock
    private BookOrderRepository bookOrderRepository;

    @Mock
    private BookOrderStateProvider bookOrderStateProvider;

    @Mock
    private NewStateExecutor newStateExecutor;


    @InjectMocks
    private BookOrderService bookOrderService;


    @Test
    public void testCreateOrderMustReturnNew(){
        //setup mock
        BookOrderEntity bookOrderEntity = new BookOrderEntity();

        when(bookOrderRepository.save(any())).thenReturn(bookOrderEntity);
        when(bookOrderStateProvider.getState(OrderState.NEW)).thenReturn(newStateExecutor);
        when(newStateExecutor.executeOrder(bookOrderEntity)).thenReturn(OrderState.NEW);

        Mockito.verify(bookOrderRepository, Mockito.times(1)).save(any(BookOrderEntity.class));
    }
}
