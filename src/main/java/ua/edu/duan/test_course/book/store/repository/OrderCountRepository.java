package ua.edu.duan.test_course.book.store.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import ua.edu.duan.test_course.book.store.entity.OrderCountEntity;

import java.util.Optional;

public interface OrderCountRepository extends JpaRepository<OrderCountEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT oce FROM OrderCountEntity oce WHERE oce.id = :id")
    Optional<OrderCountEntity> findByIdWithLock(Long id);
}
