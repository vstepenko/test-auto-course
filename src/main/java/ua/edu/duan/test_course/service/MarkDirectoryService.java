package ua.edu.duan.test_course.service;

import org.springframework.stereotype.Service;
import ua.edu.duan.test_course.entity.MarkEntity;

import java.util.List;

@Service
public class MarkDirectoryService {

    public List<MarkEntity> getMarksFromDirectory() {
        return List.of(
                new MarkEntity( 'F', 59),
                new MarkEntity('E', 66),
                new MarkEntity('D', 74),
                new MarkEntity('C', 81),
                new MarkEntity('B', 89),
                new MarkEntity('A', 100)
        );
    }
}
