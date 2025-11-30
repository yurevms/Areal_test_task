package com.example.areal_test_task.Controller;

import com.example.areal_test_task.Model.Department;
import com.example.areal_test_task.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public String getAllDepartments(Model model) {
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments", departments);
        return "departments"; // шаблон departments.html
    }

    @GetMapping("/create")
    public String createDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "department-create"; // шаблон department-create.html
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute Department department) {
        departmentService.create(department);
        return "redirect:/departments";
    }
}
