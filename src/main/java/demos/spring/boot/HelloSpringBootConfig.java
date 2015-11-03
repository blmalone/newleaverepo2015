package demos.spring.boot;

import static demos.spring.boot.CourseDifficulty.ADVANCED;
import static demos.spring.boot.CourseDifficulty.BEGINNER;
import static demos.spring.boot.CourseDifficulty.INTERMEDIATE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloSpringBootConfig {

    @Bean(name = "courses")
    public CourseList courses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("AB12", "Intro to Scala", BEGINNER, 4));
        courses.add(new Course("CD34", "JEE Web Development", INTERMEDIATE, 3));
        courses.add(new Course("EF56", "Meta-Programming in Ruby", ADVANCED, 2));
        courses.add(new Course("GH78", "OO Design with UML", BEGINNER, 3));
        courses.add(new Course("IJ90", "Database Access with JPA", INTERMEDIATE, 3));
        courses.add(new Course("KL12", "Design Patterns in C#", ADVANCED, 2));
        courses.add(new Course("MN34", "Relational Database Design", BEGINNER, 4));
        courses.add(new Course("OP56", "Writing MySql Stored Procedures", INTERMEDIATE, 1));
        courses.add(new Course("QR78", "Patterns of Parallel Programming", ADVANCED, 2));
        courses.add(new Course("ST90", "C++ Programming for Beginners", BEGINNER, 5));
        courses.add(new Course("UV12", "UNIX Threading with PThreads", INTERMEDIATE, 2));
        courses.add(new Course("WX34", "Writing Linux Device Drivers", ADVANCED, 3));
        return new CourseList(courses);
    }
}
