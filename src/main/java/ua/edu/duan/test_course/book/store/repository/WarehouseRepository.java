package ua.edu.duan.test_course.book.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.duan.test_course.book.store.entity.WarehouseEntity;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
}