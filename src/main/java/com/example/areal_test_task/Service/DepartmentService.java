package com.example.areal_test_task.Service;

import com.example.areal_test_task.Model.Department;
import com.example.areal_test_task.Repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    //получить все отделы
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    //создать отдел
    public Department create(Department department) {
        return departmentRepository.save(department);
    }
}