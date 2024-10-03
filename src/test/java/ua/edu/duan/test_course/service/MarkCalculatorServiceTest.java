package ua.edu.duan.test_course.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ua.edu.duan.test_course.entity.MarkEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MarkCalculatorServiceTest {

    @Mock
    private MarkDirectoryService directoryService;

    @InjectMocks
    private MarkCalculator markCalculator;

    @BeforeEach
    void setUp() {
        Mockito.when(directoryService.getMarksFromDirectory()).thenReturn(
                List.of(
                        new MarkEntity( 'F', 59),
                        new MarkEntity('E', 66),
                        new MarkEntity('D', 74),
                        new MarkEntity('C', 81),
                        new MarkEntity('B', 89),
                        new MarkEntity('A', 100)
                ));
    }

    @Test
    public void testIllegalArguments() {
        assertThrows(RuntimeException.class, ()-> markCalculator.returnMark(-20));
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "0, F",
                    "60, E",
                    "67, D",
                    "75, C",
                    "82, B",
                    "90, A",
                    "59, F",
                    "66, E",
                    "74, D",
                    "81, C",
                    "89, B",
                    "100, A"
            }
    )
    public void testSuccessfulResult(int point, char expected){
        char result = markCalculator.returnMark(point);
        assertEquals(expected, result);
    }
}
