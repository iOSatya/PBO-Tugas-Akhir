import javax.swing.SwingUtilities;

public class LibraryManagementSystem {
    public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                                new LoginFrame();
            }
        });
    }
}
