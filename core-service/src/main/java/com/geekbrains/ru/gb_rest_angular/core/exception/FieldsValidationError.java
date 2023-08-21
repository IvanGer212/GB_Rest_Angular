package com.geekbrains.ru.gb_rest_angular.core.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class FieldsValidationError {
    private List<String> errorFieldsMessages;

    public FieldsValidationError(List<String> errorFieldsMessages) {
        this.errorFieldsMessages = errorFieldsMessages;
    }
}
