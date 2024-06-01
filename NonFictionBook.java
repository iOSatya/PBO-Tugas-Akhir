public class NonFictionBook extends Book {
    private String subject;

    public NonFictionBook(String title, String author, String subject) {
        super(title, author);
        this.subject = subject;
    }

    @Override
    public String getDetails() {
        return "Non-Fiction Book: " + title + " by " + author + " - Subject: " + subject;
    }
}