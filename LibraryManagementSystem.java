import javax.swing.SwingUtilities;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Pastikan ini dijalankan di event dispatch thread untuk Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryFrame();
            }
        });
    }
}