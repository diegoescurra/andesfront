package com.example.andesfront.dto;

import lombok.Data;
import lombok.ToString;

import javax.management.relation.Role;
import java.util.List;

@Data
public class UserDTO {

    private String username;
    private String password;
}
