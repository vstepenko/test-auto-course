package ua.edu.duan.test_course.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ua.edu.duan.test_course.repository.MarkDirectoryRepository;

import java.util.ArrayList;

@SpringBootTest
public class MarkDirectoryServiceTest {

    @Mock
    private MarkDirectoryRepository markDirectoryRepository;

    @InjectMocks
    private MarkDirectoryService markDirectoryService;


    @Test
    void testDirectoryServiceCallDBOnlyOneTime(){
        Mockito.when(markDirectoryRepository.findAll()).thenReturn(new ArrayList<>());
        markDirectoryService.getMarksFromDirectory();
        markDirectoryService.getMarksFromDirectory();
        markDirectoryService.getMarksFromDirectory();

        Mockito.verify(markDirectoryRepository, Mockito.times(1)).findAll();
    }
}
