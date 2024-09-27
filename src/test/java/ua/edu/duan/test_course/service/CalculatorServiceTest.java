package ua.edu.duan.test_course.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.edu.duan.test_course.RegularTest;
import ua.edu.duan.test_course.dto.StudentDto;

import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CalculatorServiceTest {

    @Autowired
    private CalculatorService calculatorService;

    @BeforeEach
    public void initTest(){
        System.out.println("This code proceed before each test");
    }

    @BeforeAll
    public static void initAllTest(){
        System.out.println("This code proceed before all test");
    }

    @AfterEach
    public void destroyTest() {
        System.out.println("This code proceed after each test");
    }

    @AfterAll
    public static void destroyAllTest(){
        System.out.println("This code proceed after all test");
    }


    @Test
    public void test_successful_add() {
        int result = calculatorService.add(1, 2);
        assertTrue(5 == result);

    }


    @RegularTest
    public void explainAssetSame() {
        StudentDto expectedStudent = new StudentDto();
        expectedStudent.setId("12");
        expectedStudent.setName("Expected Name");
        expectedStudent.setSurname("Expected Surname");
        // Do some logic which return realStudent
        StudentDto realStudent = new StudentDto();

        realStudent.setId("15");
        realStudent.setName("Real Name");
        realStudent.setSurname("Real Surname");
        assertSame(expectedStudent, realStudent);
    }

    @DisplayName("намагаємось 10 разів виконати")
    @Tag("regularTest")
    @RepeatedTest(value = 10)
    public void testSuccessfulAddRepeated() {
        int result = calculatorService.add(1, 2);
        assertEquals(5 , result);

    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    public void testSuccessfulAddManyTimesValueSource(int a) {
        int result = calculatorService.add(a, 2);
        assertEquals(3, result);

    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 2, 3",
            "5, 6, 11",
            "5, 3, 3"
    })
    public void testSuccessfulAddManyTimesCsvSource(int a, int b, int expected) {
        int result = calculatorService.add(a, b);
        assertEquals(expected, result);

    }


    @ParameterizedTest
    @MethodSource("getArgumentsForAddTest")
    public void testSuccessfulAddManyTimesMethodSource(AddMethodTestModel addMethodTestModel) {
        int result = calculatorService.add(addMethodTestModel.getA(), addMethodTestModel.getB());
        assertEquals(addMethodTestModel.getExpected(), result);
    }


    public static Stream<AddMethodTestModel> getArgumentsForAddTest(){
        return Stream.of(
                new AddMethodTestModel(1,2,3),
                new AddMethodTestModel(3,4, 7),
                new AddMethodTestModel(2,2,5)
        );
    }

    @Test
    public void testSuccessfulSubtract() {
        int result = calculatorService.subtract(3, 2);
        assertEquals(1, result);
    }

    @Test
    public void testDivideOnZero() {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->  calculatorService.divide(3, 0));
        assertEquals("Divide on 0 is impossible", exception.getMessage());
    }

}
