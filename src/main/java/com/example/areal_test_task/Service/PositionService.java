package com.example.areal_test_task.Service;

import com.example.areal_test_task.Model.Position;
import com.example.areal_test_task.Repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;

    // получить все должности
    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    // создать должность
    public Position create(Position position) {
        return positionRepository.save(position);
    }
}