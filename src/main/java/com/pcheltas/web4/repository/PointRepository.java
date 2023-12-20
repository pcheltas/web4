package com.pcheltas.web4.repository;

import com.pcheltas.web4.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findPointsByUsername(String username);
}
