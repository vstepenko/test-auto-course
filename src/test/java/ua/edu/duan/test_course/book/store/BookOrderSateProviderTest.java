package ua.edu.duan.test_course.book.store;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import ua.edu.duan.test_course.book.store.state.BaseSateExecutor;
import ua.edu.duan.test_course.book.store.state.CreatedStateExecutor;
import ua.edu.duan.test_course.book.store.state.NewStateExecutor;
import ua.edu.duan.test_course.book.store.state.ProcessingStateExecutor;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

@SpringBootTest
public class BookOrderSateProviderTest {

    @Mock
    private NewStateExecutor newStateExecutor;

    @Mock
    private CreatedStateExecutor createdStateExecutor;

    @Mock
    private ProcessingStateExecutor processingStateExecutor;


    @InjectMocks
    private BookOrderStateProvider bookOrderStateProvider;

    @ParameterizedTest
    @MethodSource("getProviderModels")
    public void testProvider(ProviderTestModel providerTestModel) {
        BaseSateExecutor baseSateExecutor = bookOrderStateProvider.getState(providerTestModel.getOrderState());
        assertThat(baseSateExecutor, instanceOf(providerTestModel.getExpectedClass()));
    }


    private static Stream<ProviderTestModel> getProviderModels() {
        return Stream.of(
                new ProviderTestModel(OrderState.NEW, NewStateExecutor.class),
                new ProviderTestModel(OrderState.CREATED, CreatedStateExecutor.class),
                new ProviderTestModel(OrderState.PROCESSING, ProcessingStateExecutor.class)
        );
    }

}
