package lab05_lab06.task6_9;

import java.io.Serializable;

public class Student implements Serializable {

    String name;
    String surname;
    int year;
    char letter;

    public Student(String name, String surname, int year, char letter) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.letter = letter;
    }

    public String getClassName() {
        return year + " " + letter;
    }

    public String toString() {
        return surname + " " + name + ", " + getClassName();
    }
}