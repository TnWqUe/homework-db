package ru.mpei.example.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.mpei.example.domain.Student;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Repository
public class StudentDaoJdbc implements StudentDao{
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public StudentDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }
    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from students", Integer.class);
        return count == null? 0: count;
    }
    //проверка существования группы
    @Override
    public int checkGroup(String my_group) {
        Map<String, Object> params = Collections.singletonMap("my_group", my_group);
        Integer count = namedParameterJdbcOperations.queryForObject("select count(my_group) from students where my_group = :my_group",params, Integer.class);
        return count == null? 0: count;
    }

//метод создания студента
    //С учетом того, есть ли такая группа или ее нет
    @Override
    public void insert(Student student) {
        int count = checkGroup(student.getMy_group());
        if (count!=0){
            //такая группа уже есть в БД
            namedParameterJdbcOperations.update("insert into students (id, name, my_group, course, phone) values (:id, :name, :my_group, :course, :phone)",
                    Map.of("id", student.getId(), "name", student.getName(), "my_group", student.getMy_group(), "course", student.getCourse(),"phone", student.getPhone()));

        }
        else{
            namedParameterJdbcOperations.update("insert into students (id, name, my_group, course, phone) values (:id, :name, :my_group, :course, :phone)",
                    Map.of("id", student.getId(), "name", student.getName(), "my_group", student.getMy_group(), "course", student.getCourse(),"phone", student.getPhone()));
        }
    }
    @Override
    public List<Student> getAllByGroup(String my_group) {
        Map<String, Object> params = Collections.singletonMap("my_group", my_group);
        return namedParameterJdbcOperations.query("select id, name, my_group, course, phone from students where my_group = :my_group", params, new StudentMapper());
    }

    @Override
    public Student getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id,name,my_group,course,phone from students where id = :id", params,new StudentMapper()
        );
    }

    //вывод студентов и их телефонов по названию группы
    @Override
    public Set<String> getByGroup(String my_group) {
        List<Student> students = getAllByGroup(my_group);
        Set<String> result = new HashSet<>();
        for (int i =0; i< students.size();i++) {
            result.add(students.get(i).getName()+" "+students.get(i).getPhone());
        }
        return result;
    }

    private static class StudentMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String my_group = resultSet.getString("my_group");
            String course = resultSet.getString("course");
            String phone = resultSet.getString("phone");
            return new Student(id, name, my_group, course, phone);
        }
    }
}
