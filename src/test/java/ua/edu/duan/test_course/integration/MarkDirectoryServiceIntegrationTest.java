package ua.edu.duan.test_course.integration;

import org.apache.tomcat.util.http.parser.MediaType;
import org.hibernate.annotations.processing.SQL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;
import ua.edu.duan.test_course.entity.MarkEntity;
import ua.edu.duan.test_course.service.MarkDirectoryService;

import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@Sql("/test-data.sql")
public class MarkDirectoryServiceIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMarkDirectoryService() throws Exception {

        this.mockMvc.perform(get("/marks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].mark").value("F"))
                .andExpect(jsonPath("[0].maxPoint").value(59));
        ;


    }
}
