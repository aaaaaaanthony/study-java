package org.example;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserVO {

    @NotBlank(message = "姓名是必须的")
    String name;

    int age;
}
