package ru.mpei.example.dao;

import ru.mpei.example.domain.Student;

import java.util.List;
import java.util.Set;

public interface StudentDao {
    int count();
    //проверка существования группы
    int checkGroup(String my_group);
    //добавление студента
    void insert(Student student);
    Student getById(long id);
    List<Student> getAllByGroup(String my_group);

    //вывод студентов и их телефонов по названию группы
    Set<String> getByGroup(String my_group);


}
