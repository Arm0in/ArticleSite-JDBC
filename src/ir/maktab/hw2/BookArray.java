package ir.maktab.hw2;

public class BookArray {
    public static void main(String[] args) {
        Book[] books = {
                new Fiction("Frankenstein"),
                new Fiction("Invisible Man"),
                new Fiction("Harry Potter"),
                new Fiction("Les Miserables"),
                new Fiction("The Little Prince"),
                new Fiction("Moby Dick"),
                new NonFiction("Java for Dummies"),
                new NonFiction("A Brief History of Time"),
                new NonFiction("The History of the Ancient World"),
                new NonFiction("The Crusades")
        };

        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i]);
            System.out.println("========================");
        }
    }
}
