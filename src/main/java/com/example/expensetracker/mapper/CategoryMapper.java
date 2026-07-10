package com.example.expensetracker.mapper;

import com.example.expensetracker.model.dto.CategoryDto;
import com.example.expensetracker.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryDto dto);

    @Mapping(target = "id", ignore = true)
    void updateCategoryFromDto(CategoryDto dto, @MappingTarget Category category);

}
