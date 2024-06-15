import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; 
    }

    
    public boolean removeBook(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                books.remove(i);
                return true; 
            }
        }
        return false; 
    }

    
    public boolean changeBookAvailability(String title, boolean available) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setAvailability(available);
                return true; 
            }
        }
        return false; 
    }
    
    public String getAllBooksAsString() {
        if (books.isEmpty()) {
            return "No books available.";
        }
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book.getDetails()).append("\n");
        }
        return sb.toString();
    }
}
