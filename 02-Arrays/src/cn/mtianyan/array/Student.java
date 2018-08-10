package cn.mtianyan.array;

import java.util.Objects;

public class Student {

    private String name;
    private int score;

    public Student(String studentName, int studentScore) {
        name = studentName;
        score = studentScore;
    }

    @Override
    public String toString() {
        return String.format("Student(name: %s, score: %d)", name, score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return score == student.score &&
                (name.equals(student.name));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }

    public static void main(String[] args) {

        Array<Student> arr = new Array();
        arr.addLast(new Student("mtianyan1", 100));
        arr.addLast(new Student("mtianyan2", 66));
        arr.addLast(new Student("mtianyan3", 88));
        arr.addLast(new Student("mtianyan3", 88));

        System.out.println("================start=======================");
        System.out.println(arr);
        arr.remove(1);
        Student stu1 = new Student("mtianyan1", 100);
        Student stu3 = new Student("mtianyan3", 88);
        arr.removeElement(stu1);
        System.out.println(arr);
        System.out.println("==============================================");
        System.out.println(arr);
        arr.removeAllElement(stu3);
        System.out.println(arr);
    }
}
