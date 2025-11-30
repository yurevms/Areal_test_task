package com.example.areal_test_task.Controller;

import com.example.areal_test_task.Model.Employee;
import com.example.areal_test_task.Service.DepartmentService;
import com.example.areal_test_task.Service.EmployeeService;
import com.example.areal_test_task.Service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final PositionService positionService;

    // Главная страница с таблицей сотрудников и фильтрами
    @GetMapping
    public String getAllEmployees(Model model,
                                  @RequestParam(required = false) Long departmentId,
                                  @RequestParam(required = false) Long positionId) {
        List<Employee> employees;

        if (departmentId != null) {
            employees = employeeService.getByDepartment(departmentId);
        } else if (positionId != null) {
            employees = employeeService.getByPosition(positionId);
        } else {
            employees = employeeService.getAll();
        }

        model.addAttribute("employees", employees);
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("positions", positionService.getAll());

        return "employees"; // шаблон employees.html
    }

    // Поиск по ФИО
    @GetMapping("/findByName")
    public String findByName(@RequestParam String fullName, Model model) {
        List<Employee> employees = employeeService.searchByFullName(fullName);
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("positions", positionService.getAll());
        return "employees";
    }

    // Страница редактирования сотрудника
    @GetMapping("/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("positions", positionService.getAll());
        return "employee-edit"; // шаблон employee-edit.html
    }

    // Обновление сотрудника
    @PostMapping("/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @ModelAttribute Employee employee) {
        employeeService.update(id, employee);
        return "redirect:/employees";
    }

    // Увольнение сотрудника
    @PostMapping("/fire/{id}")
    public String fireEmployee(@PathVariable Long id) {
        employeeService.fire(id);
        return "redirect:/employees";
    }

    // Создание нового сотрудника
    @GetMapping("/create")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("positions", positionService.getAll());
        return "employee-create"; // шаблон employee-create.html
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute Employee employee) {
        employeeService.create(employee);
        return "redirect:/employees";
    }
}
