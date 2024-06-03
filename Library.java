import java.util.ArrayList;
import java.util.List;

// Kelas untuk menyimpan daftar buku
public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    // Method untuk menambah buku ke perpustakaan
    public void addBook(Book book) {
        books.add(book);
    }

    // Method untuk mendapatkan daftar semua buku
    public List<Book> getBooks() {
        return books;
    }

    // Method untuk mencari buku berdasarkan judul
    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Mengembalikan null jika buku tidak ditemukan
    }
}