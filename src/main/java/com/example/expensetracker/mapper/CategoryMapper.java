package com.example.expensetracker.mapper;

import com.example.expensetracker.dto.CategoryDto;
import com.example.expensetracker.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryDto dto);

    void updateCategoryFromDto(CategoryDto dto, @MappingTarget Category category);

}
