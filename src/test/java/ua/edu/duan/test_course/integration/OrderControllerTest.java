package ua.edu.duan.test_course.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ua.edu.duan.test_course.book.store.OrderState;
import ua.edu.duan.test_course.book.store.entity.BookOrderEntity;
import ua.edu.duan.test_course.book.store.repository.BookOrderRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookOrderRepository bookOrderRepository;

    @Test
    public void successfulOrderTest() throws Exception {
        List<BookOrderEntity> bookOrderEntities = bookOrderRepository.findAll();

        assertEquals(bookOrderEntities.size(), 0);

        mockMvc.perform(post("/book-store/order").param("itemId", "1"))
                .andExpect(status().isOk());
        bookOrderEntities = bookOrderRepository.findAll();

        assertEquals(bookOrderEntities.size(), 1);
        assertEquals(bookOrderEntities.get(0).getState(), OrderState.CREATED);
    }

}
