package com.example.areal_test_task.Repository;

import com.example.areal_test_task.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}