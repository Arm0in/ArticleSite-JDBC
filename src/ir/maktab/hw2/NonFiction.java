package ir.maktab.hw2;

public class NonFiction extends Book {
    @Override
    public void setPrice() {
        this.price = 37.99;
    }

    @Override
    public String toString() {
        return "Genre: " + getClass().getSimpleName() +
                "\nTitle: " + getTitle() +
                "\nPrice: " + getPrice();
    }

    public NonFiction(String title) {
        super(title);
        setPrice();
    }
}
