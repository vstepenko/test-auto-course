package ua.edu.duan.test_course.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ua.edu.duan.test_course.book.store.entity.OrderCountEntity;
import ua.edu.duan.test_course.book.store.repository.BookOrderRepository;
import ua.edu.duan.test_course.book.store.repository.OrderCountRepository;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
public class OrderPerformanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookOrderRepository bookOrderRepository;

    @Autowired
    private OrderCountRepository orderCountRepository;

    @Test
    void testOrderPerformance() throws Exception {
        assertEquals(bookOrderRepository.findAll().size(), 0);
        OrderCountEntity orderCountEntity = orderCountRepository.findAll().get(0);
        assertEquals(orderCountEntity.getCount(), 0);

        CountDownLatch latch = new CountDownLatch(1000);

        for (int i = 0; i < 1000; i++) {
         Thread thread = new Thread(
                 ()->{
                     try {
                         mockMvc.perform(post("/book-store/order").param("itemId", "1"))
                                 .andExpect(status().isOk());
                         latch.countDown();
                     } catch (Exception e) {
                         throw new RuntimeException(e);
                     }
                 });
            thread.start();
        }
        latch.await();

        assertEquals(1000, bookOrderRepository.findAll().size());
        orderCountEntity = orderCountRepository.findAll().get(0);
        assertEquals(1000, orderCountEntity.getCount());
    }
}
