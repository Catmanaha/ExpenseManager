package com.example.expensetracker.mapper;

import com.example.expensetracker.mapper.models.TransactionMapperModel;
import com.example.expensetracker.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "id", ignore = true)
    Transaction toTransaction(TransactionMapperModel mm);

    @Mapping(target = "id", ignore = true)
    void updateTransactionFromMm(TransactionMapperModel mm, @MappingTarget Transaction transaction);

}
