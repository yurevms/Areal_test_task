package com.example.areal_test_task.Service;

import com.example.areal_test_task.Model.Employee;
import com.example.areal_test_task.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Получить сотрудника по id
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
    }

    // получить всех сотрудников
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    // поиск по ФИО (частичное совпадение)
    public List<Employee> searchByFullName(String fullName) {
        return employeeRepository.findByFullNameContainingIgnoreCase(fullName);
    }

    // фильтр по отделу
    public List<Employee> getByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    // фильтр по должности
    public List<Employee> getByPosition(Long positionId) {
        return employeeRepository.findByPositionId(positionId);
    }

    // создать сотрудника
    public Employee create(Employee employee) {
        employee.setFired(false); // новый сотрудник не уволен
        return employeeRepository.save(employee);
    }

    // обновить данные сотрудника
    public Employee update(Long id, Employee updated) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));

        if (Boolean.TRUE.equals(existing.getFired())) {
            throw new IllegalStateException("Нельзя редактировать уволенного сотрудника");
        }

        existing.setFullName(updated.getFullName());
        existing.setBirthDate(updated.getBirthDate());
        existing.setPassportNumber(updated.getPassportNumber());
        existing.setContactInfo(updated.getContactInfo());
        existing.setAddress(updated.getAddress());
        existing.setDepartment(updated.getDepartment());
        existing.setPosition(updated.getPosition());
        existing.setSalary(updated.getSalary());
        existing.setHireDate(updated.getHireDate());

        return employeeRepository.save(existing);
    }

    // уволить сотрудника
    public Employee fire(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));

        employee.setFired(true);

        return employeeRepository.save(employee);
    }
}