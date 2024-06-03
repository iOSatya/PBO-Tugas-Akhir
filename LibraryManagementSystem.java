import javax.swing.SwingUtilities;

// Kelas utama untuk menjalankan aplikasi
public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Pastikan ini dijalankan di event dispatch thread untuk Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Membuat dan menampilkan jendela utama aplikasi
                new LibraryFrame();
            }
        });
    }
}