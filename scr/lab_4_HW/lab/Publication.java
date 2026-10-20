package lab_4_HW.lab;

// 1. Інтерфейс Видання
interface Printable {
    void printInfo();
}

// 2. Абстрактний клас Видання, реалізує інтерфейс
abstract class Publication implements Printable {
    protected String title;
    protected int year;

    public Publication(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public abstract void showSpecificInfo();
}

// 3. Клас Книга
class Book extends Publication {
    private String author;

    public Book(String title, int year, String author) {
        super(title, year);
        this.author = author;
    }

    @Override
    public void printInfo() {
        System.out.println("Книга: \"" + title + "\", Автор: " + author + ", Рік: " + year);
    }

    @Override
    public void showSpecificInfo() {
        System.out.println("-> Це художня або навчальна книга автора " + author);
    }
}

// 4. Клас Довідник
class Directory extends Publication {
    private String industry;

    public Directory(String title, int year, String industry) {
        super(title, year);
        this.industry = industry;
    }

    @Override
    public void printInfo() {
        System.out.println("Довідник: \"" + title + "\", Галузь: " + industry + ", Рік: " + year);
    }

    @Override
    public void showSpecificInfo() {
        System.out.println("-> Це довідкове видання для галузі: " + industry);
    }
}

// 5. Клас Енциклопедія
class Encyclopedia extends Publication {
    private int volumesCount;

    public Encyclopedia(String title, int year, int volumesCount) {
        super(title, year);
        this.volumesCount = volumesCount;
    }

    @Override
    public void printInfo() {
        System.out.println("Енциклопедія: \"" + title + "\", Кількість томів: " + volumesCount + ", Рік: " + year);
    }

    @Override
    public void showSpecificInfo() {
        System.out.println("-> Це багатотомне енциклопедичне видання (томів: " + volumesCount + ")");
    }
}
