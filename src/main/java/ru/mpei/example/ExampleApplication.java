package ru.mpei.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.h2.tools.Console;
import ru.mpei.example.dao.StudentDao;
import ru.mpei.example.domain.Student;

@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args) throws Exception {

		ApplicationContext context = SpringApplication.run(ExampleApplication.class);

		StudentDao dao = context.getBean(StudentDao.class);

		dao.insert(new Student(2, "Sveta", "А-07-22","Основы Linux","89667777777"));
		dao.insert(new Student(3, "Sveta", "А-07-22","СУБД","89667777777"));
		dao.insert(new Student(4, "Sveta", "А-07-22","ЛВС","89667777777"));

		dao.insert(new Student(5, "Violeta", "А-08-21","Основы Java","89661111111"));
		dao.insert(new Student(6, "Violeta", "А-08-21","Разработка ПО","89661111111"));
		dao.insert(new Student(7, "Violeta", "А-08-21","ЛВС","89661111111"));

		dao.insert(new Student(8, "Tom", "А-02-20","ЛВС","89662222222"));
		dao.insert(new Student(9, "Tom", "А-02-20","Основы Java","89662222222"));
		dao.insert(new Student(10, "Tom", "А-02-20","СУБД","89662222222"));

		dao.insert(new Student(11, "Lena", "А-01-22","Разработка ПО","89661111110"));
		dao.insert(new Student(12, "Lena", "А-01-22","Основы Linux","89661111110"));
		dao.insert(new Student(13, "Lena", "А-01-22","СУБД","89661111110"));


		dao.insert(new Student(14, "Olga", "А-03-22","Разработка ПО","89661111011"));
		dao.insert(new Student(15, "Olga", "А-03-22","Основы Linux","89661111011"));
		dao.insert(new Student(16, "Olga", "А-03-22","Основы Java","89661111011"));
		dao.insert(new Student(17, "Olga", "А-03-22","СУБД","89661111011"));

		dao.insert(new Student(18, "Sergey", "А-08-21","Основы Java","89991111111"));
		dao.insert(new Student(19, "Sergey", "А-08-21","Разработка ПО","89991111111"));

		dao.insert(new Student(20, "Mary", "А-08-21","Основы Java","89011111111"));
		dao.insert(new Student(21, "Mary", "А-08-21","Разработка ПО","89011111111"));

		dao.insert(new Student(22, "Lera", "А-08-21","Основы Linux","89021111111"));
		dao.insert(new Student(23, "Lera", "А-08-21","СУБД","89021111111"));

		dao.insert(new Student(24, "Mark", "А-07-22","Разработка ПО","89047777777"));
		dao.insert(new Student(25, "Mark", "А-07-22","СУБД","89047777777"));

		dao.insert(new Student(26, "Tamara", "А-07-22","Основы Linux","89087777777"));
		dao.insert(new Student(27, "Tamara", "А-07-22","СУБД","89087777777"));

		dao.insert(new Student(28, "Tom", "А-02-20","ЛВС","89662222222"));
		dao.insert(new Student(29, "Tom", "А-02-20","Основы Java","89662222222"));

		dao.insert(new Student(30, "Anton", "А-02-20","СУБД","89663333333"));
		dao.insert(new Student(31, "Anton", "А-02-20","Основы Linux","89663333333"));


		dao.insert(new Student(32, "Egor", "А-03-22","Разработка ПО","89661111088"));
		dao.insert(new Student(33, "Egor", "А-03-22","Основы Linux","89661111088"));

		dao.insert(new Student(34, "Vladimir", "А-03-22","Разработка ПО","89661111044"));
		dao.insert(new Student(35, "Vladimir", "А-03-22","ЛВС","89661111044"));

		String group="А-07-22";

		System.out.println("Вывод студентов с телефонами по заданной группе "+group+": "+dao.getByGroup(group));

		Console.main(args);
	}

}
