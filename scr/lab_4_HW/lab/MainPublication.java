package lab_4_HW.lab;

public class MainPublication {
    public static void main(String[] args) {
        Publication[] publications = {
                new Book("Алгоритми та структури даних", 2023, "Джон Доу"),
                new Directory("Телефонний довідник Києва", 2024, "Зв'язок"),
                new Encyclopedia("Велика українська енциклопедія", 2022, 10)
        };

        for (Publication publication : publications) {
            publication.printInfo();
            publication.showSpecificInfo();
            System.out.println("-------------------------");
        }
    }
}