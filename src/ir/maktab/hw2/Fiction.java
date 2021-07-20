package ir.maktab.hw2;

public class Fiction extends Book {
    @Override
    public void setPrice() {
        this.price = 24.99;
    }

    @Override
    public String toString() {
        return "Genre: " + getClass().getSimpleName() +
                "\nTitle: " + getTitle() +
                "\nPrice: " + getPrice();
    }

    public Fiction(String title) {
        super(title);
        setPrice();
    }
}
