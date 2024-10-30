package ua.edu.duan.test_course.book.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "oder_count")
public class OrderCountEntity {

    @Id
    private long id;

    private long count;
}
