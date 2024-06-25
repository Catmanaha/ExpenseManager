package com.example.expensetracker.mapper;

import com.example.expensetracker.model.dto.CategoryDto;
import com.example.expensetracker.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryDto dto);

    void updateCategoryFromDto(CategoryDto dto, @MappingTarget Category category);

}
