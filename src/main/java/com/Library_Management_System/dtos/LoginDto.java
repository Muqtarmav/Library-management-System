package com.Library_Management_System.dtos;

import lombok.Data;

@Data
public class LoginDto {

    private String userNameOrEmail;
    private String passWord;
}
