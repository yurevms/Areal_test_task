package com.example.areal_test_task.Repository;

import com.example.areal_test_task.Model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}