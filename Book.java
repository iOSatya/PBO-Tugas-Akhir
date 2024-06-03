// Kelas abstract untuk Buku
public abstract class Book {
    protected String title;
    protected String author;
    protected boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Default buku tersedia
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean available) {
        isAvailable = available;
    }

    // Method abstract untuk mendapatkan detail buku
    public abstract String getDetails();
}