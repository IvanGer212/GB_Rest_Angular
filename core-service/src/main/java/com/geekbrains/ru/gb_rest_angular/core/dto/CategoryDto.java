package com.geekbrains.ru.gb_rest_angular.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

        private Long id;
        private String title;
        private Long parentId;


}
