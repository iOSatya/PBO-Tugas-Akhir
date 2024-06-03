// Kelas untuk Buku Fiksi yang di inherit dari kelas Book
public class FictionBook extends Book {
    private String genre;

    public FictionBook(String title, String author, String genre) {
        super(title, author);
        this.genre = genre;
    }

    @Override
    public String getDetails() {
        return "Fiction Book: " + title + " by " + author + " - Genre: " + genre;
    }
}