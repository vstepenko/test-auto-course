package ua.edu.duan.test_course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.duan.test_course.entity.MarkEntity;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class MarkCalculator {

    private final MarkDirectoryService directoryService;

    public char returnMark(int point) {

        if (point < 0) {
            throw new RuntimeException("point must be positive");
        }

        return directoryService.getMarksFromDirectory()
                .stream()
                .filter(markEntity -> markEntity.getMaxPoint() >= point)
                .min(Comparator.comparingInt(MarkEntity::getMaxPoint))
                .map(MarkEntity::getMark).orElseThrow(() -> new RuntimeException("mark not suitable"));

    }
}
