package com.example.expensetracker.service;

import com.example.expensetracker.model.dto.CategoryDto;
import com.example.expensetracker.mapper.CategoryMapper;
import com.example.expensetracker.model.entity.Category;
import com.example.expensetracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService {
    final CategoryRepository repository;
    final CategoryMapper mapper;

    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void create(CategoryDto dto) {
        repository.save(mapper.toCategory(dto));
    }

    public void update(UUID id, CategoryDto dto) {
        Category category = repository.findById(id).orElseThrow(RuntimeException::new);
        mapper.updateCategoryFromDto(dto, category);
        repository.save(category);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public Category getById(UUID id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }
}
