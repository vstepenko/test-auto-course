package ua.edu.duan.test_course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mark_directory")
public class MarkEntity {
    @Id
    @Column(name = "mark")
    Character mark;

    @Column(name = "max_point")
    int maxPoint;
}
