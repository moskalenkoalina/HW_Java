package lab_4_HW;

public class Student {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private int birthYear;
    private String address;
    private String phone;
    private String faculty;
    private int course;
    private String group;

    public Student() {
    }

    // Конструктор з усіма параметрами
    public Student(int id, String lastName, String firstName, String middleName,
                   int birthYear, String address, String phone,
                   String faculty, int course, String group) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthYear = birthYear;
        this.address = address;
        this.phone = phone;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    public Student(int id, String lastName, String firstName, String faculty, int course, String group) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    // Гетери та сетери
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Студент: " + lastName + " " + firstName + " " + middleName +
                ", рік: " + birthYear + ", факультет: " + faculty +
                ", курс: " + course + ", група: " + group;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id == student.id && birthYear == student.birthYear && course == student.course;
    }

    @Override
    public int hashCode() {
        return id + birthYear + course;
    }
}