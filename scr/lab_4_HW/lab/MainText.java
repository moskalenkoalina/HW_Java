package lab_4_HW.lab;

public class MainText {
    public static void main(String[] args) {
        // Створюємо слова для першого речення
        Word[] w1 = {new Word("Сьогодні"), new Word("чудовий"), new Word("день")};
        Sentence s1 = new Sentence(w1);

        // Створюємо слова для другого речення
        Word[] w2 = {new Word("Ми"), new Word("вивчаємо"), new Word("мову"), new Word("Java")};
        Sentence s2 = new Sentence(w2);

        // Створюємо об'єкт класу Текст
        Text myText = new Text("Лабораторна робота", new Sentence[]{s1, s2});

        // Виводимо початковий текст на консоль (зберігаючи заголовок)
        myText.printText();

        // Доповнюємо текст новим реченням
        Word[] w3 = {new Word("Скоро"), new Word("буде"), new Word("сесія")};
        Sentence s3 = new Sentence(w3);
        myText.appendSentence(s3);

        // Виводимо текст після доповнення
        System.out.println("\n--- Після доповнення тексту ---");
        myText.printText();
    }
}