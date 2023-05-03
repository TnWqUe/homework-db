package ru.mpei.example.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Student {
    private final long id;
    private final String name;
    private final String my_group;
    private final String course;
    private final String phone;
}
