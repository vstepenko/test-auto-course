package ua.edu.duan.test_course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.duan.test_course.entity.MarkEntity;

public interface MarkDirectoryRepository extends JpaRepository<MarkEntity, Character> {
}
