package ua.edu.duan.test_course.book.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.duan.test_course.book.store.state.BaseSateExecutor;
import ua.edu.duan.test_course.book.store.state.CancelledStateExecutor;
import ua.edu.duan.test_course.book.store.state.ComletedStateExecutor;
import ua.edu.duan.test_course.book.store.state.CreatedStateExecutor;
import ua.edu.duan.test_course.book.store.state.DeliveredStateExecutor;
import ua.edu.duan.test_course.book.store.state.NewStateExecutor;
import ua.edu.duan.test_course.book.store.state.ProcessingStateExecutor;

@Component
@RequiredArgsConstructor
public class BookOrderStateProvider {

    private final NewStateExecutor newStateExecutor;
    private final CreatedStateExecutor createdStateExecutor;
    private final ProcessingStateExecutor processingStateExecutor;
    private final DeliveredStateExecutor deliveredStateExecutor;
    private final CancelledStateExecutor cancelledStateExecutor;
    private final ComletedStateExecutor comletedStateExecutor;

    public BaseSateExecutor getState(OrderState orderState) {
        switch (orderState){
            case NEW :
                return newStateExecutor;
            case CREATED :
                return createdStateExecutor;
            case PROCESSING :
                return processingStateExecutor;
            case DELIVERED :
                return deliveredStateExecutor;
            case CANCELLED :
                return cancelledStateExecutor;
            case COMPLETED :
                return comletedStateExecutor;
            default:
                throw new RuntimeException("Unknown order state: " + orderState);

        }
    }
}
