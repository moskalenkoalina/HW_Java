package lab_4;

public abstract class Stone {
    private String name;
    private double weight; // в каратах
    private double pricePerCarat; // вартість за 1 карат
    private int transparency; // прозорість у відсотках

    public Stone() {}

    public Stone(String name, double weight, double pricePerCarat, int transparency) {
        this.name = name;
        this.weight = weight;
        this.pricePerCarat = pricePerCarat;
        this.transparency = transparency;
    }

    // Загальна вартість каменю
    public double getTotalPrice() {
        return weight * pricePerCarat;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getPricePerCarat() { return pricePerCarat; }
    public void setPricePerCarat(double pricePerCarat) { this.pricePerCarat = pricePerCarat; }

    public int getTransparency() { return transparency; }
    public void setTransparency(int transparency) { this.transparency = transparency; }

    @Override
    public String toString() {
        return "Камінь: " + name + ", вага: " + weight + " кар., ціна: " + getTotalPrice() + "$, прозорість: " + transparency + "%";
    }
}
