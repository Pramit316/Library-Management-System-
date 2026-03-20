package model;

public class Book {

    String title;
    String author;
    boolean available;
    float price;
    int quantity;

    public Book(String title, String author, boolean available , float price, int quantity) {
        this.title = title;
        this.author = author;
        this.available = available;
        this.price = price;
        this.quantity = quantity;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return  "================================================================\n" +
                "Title: " + title + "\nAuthor Name: " + author + "\nAvailable: " + available + "\nPrice: " + price +
        "\n================================================================\n";
    }
}
