package lab_4_HW.lab;

public class Connection {
    private static int counter = 1;
    private int id;

    Connection() {
        this.id = counter++;
    }

    public void doWork() {
        System.out.println("Використовується підключення №" + id);
    }
}