package lab_4;

public class task4_1_hw {

    public static void main(String[] args) {
        Student[] students = new Student[5];

        students[0] = new Student(1, "Москаленко", "Аліна", "Романівна", 2005, "Київ", "380671112233", "Мех-мат", 2, "комп. мат");
        students[1] = new Student(2, "Шевченко", "Марія", "Олегівна", 2004, "Львів", "380502223344", "Мех-мат", 1, "комп. мат");
        students[2] = new Student(3, "Бондаренко", "Олексій", "Ігорович", 2002, "Харків", "380633334455", "ФІТ", 3, "ІТ-31");
        students[3] = new Student(4, "Мельник", "Анна", "Сергіївна", 2003, "Одеса", "380974445566", "Мех-мат", 2, "комп. мат");
        students[4] = new Student(5, "Ткаченко", "Дмитро", "Васильович", 2005, "Дніпро", "380995556677", "ФІТ", 1, "ІТ-11");

        // а) Список студентів заданого факультету
        System.out.println("=== А) Студенти факультету Мех-мат ===");
        for (int i = 0; i < students.length; i++) {
            if (students[i].getFaculty().equals("Мех-мат")) {
                System.out.println(students[i]);
            }
        }

        // b) Списки студентів для кожного факультету і курсу
        System.out.println("\n=== Б) Списки за факультетами і курсами ===");
        String[] facList = {"Мех-мат", "ФІТ"};
        int[] courseList = {1, 2, 3};

        for (int f = 0; f < facList.length; f++) {
            for (int c = 0; c < courseList.length; c++) {
                System.out.println("Факультет: " + facList[f] + ", Курс: " + courseList[c]);
                for (int i = 0; i < students.length; i++) {
                    if (students[i].getFaculty().equals(facList[f]) && students[i].getCourse() == courseList[c]) {
                        System.out.println("   -> " + students[i]);
                    }
                }
            }
        }

        // c) Список студентів, які народилися після заданого року
        System.out.println("\n=== В) Студенти, які народилися після 2003 року ===");
        for (int i = 0; i < students.length; i++) {
            if (students[i].getBirthYear() > 2003) {
                System.out.println(students[i]);
            }
        }

        // d) Список навчальної групи
        System.out.println("\n=== Г) Студенти групи комп. мат ===");
        for (int i = 0; i < students.length; i++) {
            if (students[i].getGroup().equals("комп. мат")) {
                System.out.println(students[i]);
            }
        }
    }
}
