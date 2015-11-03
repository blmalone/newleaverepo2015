package demos.spring.boot;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CourseList {

    private final Map<String, Course> courses = new TreeMap<>();

    public CourseList() {
    }

    public CourseList(List<Course> courses) {
        for (Course course : courses) {
            this.courses.put(course.getId(), course);
        }
    }

    public Collection<Course> list() {
        return courses.values();
    }

    public void replace(Course course) {
        courses.put(course.getId(), course);
    }

    public Course get(String id) {
        return courses.get(id);
    }

    public void delete(String id) {
        courses.remove(id);
    }
}
