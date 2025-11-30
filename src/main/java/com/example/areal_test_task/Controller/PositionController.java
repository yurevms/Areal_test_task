package com.example.areal_test_task.Controller;

import com.example.areal_test_task.Model.Position;
import com.example.areal_test_task.Service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @GetMapping
    public String getAllPositions(Model model) {
        List<Position> positions = positionService.getAll();
        model.addAttribute("positions", positions);
        return "positions"; // шаблон positions.html
    }

    @GetMapping("/create")
    public String createPositionForm(Model model) {
        model.addAttribute("position", new Position());
        return "position-create"; // шаблон position-create.html
    }

    @PostMapping("/create")
    public String createPosition(@ModelAttribute Position position) {
        positionService.create(position);
        return "redirect:/positions";
    }
}
