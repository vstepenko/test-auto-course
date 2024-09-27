package ua.edu.duan.test_course.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MarkCalculatorServiceTest {


    private final MarkCalculator markCalculator = new MarkCalculator(new MarkDirectoryService());

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
