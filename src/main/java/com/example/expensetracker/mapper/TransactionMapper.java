package com.example.expensetracker.mapper;

import com.example.expensetracker.mapper.models.TransactionMapperModel;
import com.example.expensetracker.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toTransaction(TransactionMapperModel mm);

    void updateTransactionFromMm(TransactionMapperModel mm, @MappingTarget Transaction transaction);

}
