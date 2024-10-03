package ua.edu.duan.test_course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.duan.test_course.entity.MarkEntity;
import ua.edu.duan.test_course.repository.MarkDirectoryRepository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class MarkDirectoryService {

    private final MarkDirectoryRepository markDirectoryRepository;

    private CopyOnWriteArrayList<MarkEntity> markEntities;

    public List<MarkEntity> getMarksFromDirectory() {

        if (markEntities == null) {
            markEntities = new CopyOnWriteArrayList<>(markDirectoryRepository.findAll());
        }
        return markEntities;
    }
}
