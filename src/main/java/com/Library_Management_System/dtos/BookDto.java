package com.Library_Management_System.dtos;

import lombok.Data;

@Data
public class BookDto {

    private String title;
    private String author;
    private String description;
    private String category;
    private int pageCount;
}
