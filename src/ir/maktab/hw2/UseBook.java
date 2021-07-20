package ir.maktab.hw2;

public class UseBook {
    public static void main(String[] args) {
        //creating fiction book
        Fiction fiction = new Fiction("Lord of the Rings");
        System.out.println("Fiction Title: " + fiction.getTitle() + ", Price: " + fiction.getPrice());

        //creating nonfiction book
        NonFiction nonFiction = new NonFiction("Data Structures and Algorithms");
        System.out.println("NonFiction Title: " + nonFiction.getTitle() + ", Price: " + nonFiction.getPrice());
    }
}
