package lab_4_HW.lab;

// Клас Слово
class Word {
    private String content;

    public Word(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}

// Клас Речення
class Sentence {
    private Word[] words;

    public Sentence(Word[] words) {
        this.words = words;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            if (i < words.length - 1) {
                sb.append(" ");
            }
        }
        sb.append("."); // в кінці речення ставимо крапку
        return sb.toString();
    }
}

// Головний клас Текст
public class Text {
    private String title;
    private Sentence[] sentences;

    public Text(String title, Sentence[] sentences) {
        this.title = title;
        this.sentences = sentences;
    }

    // Метод: вивести заголовок тексту
    public void printTitle() {
        System.out.println("=== ЗАГОЛОВОК: " + title + " ===");
    }

    // Метод: вивести на консоль текст (разом із заголовком)
    public void printText() {
        printTitle();
        for (int i = 0; i < sentences.length; i++) {
            System.out.print(sentences[i] + " ");
        }
        System.out.println();
    }

    // Метод: доповнити текст новим реченням
    public void appendSentence(Sentence newSentence) {
        Sentence[] newArr = new Sentence[sentences.length + 1];
        for (int i = 0; i < sentences.length; i++) {
            newArr[i] = sentences[i];
        }
        newArr[newArr.length - 1] = newSentence;
        sentences = newArr;
    }
}