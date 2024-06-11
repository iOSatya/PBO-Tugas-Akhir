import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryFrame extends JFrame {
    private Library library;
    private JTextArea displayArea;

    public LibraryFrame() {
        library = new Library();
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menambahkan ikon ke jendela
        setIconImage(new ImageIcon("library_icon.png").getImage());

        // Area teks untuk menampilkan informasi buku
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Serif", Font.PLAIN, 16));
        displayArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel untuk menambah buku
        JPanel addBookPanel = new JPanel();
        addBookPanel.setLayout(new GridBagLayout());
        addBookPanel.setBorder(BorderFactory.createTitledBorder("Add New Book"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField titleField = new JTextField(15);
        JTextField authorField = new JTextField(15);
        JTextField genreOrSubjectField = new JTextField(15);
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Fiction", "Non-Fiction"});
        JButton addButton = new JButton("Add Book");

        gbc.gridx = 0;
        gbc.gridy = 0;
        addBookPanel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        addBookPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addBookPanel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1;
        addBookPanel.add(authorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        addBookPanel.add(new JLabel("Genre/Subject:"), gbc);
        gbc.gridx = 1;
        addBookPanel.add(genreOrSubjectField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        addBookPanel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        addBookPanel.add(typeComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        addBookPanel.add(addButton, gbc);

        inputPanel.add(addBookPanel);

        // Panel untuk mencari buku
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Book"));

        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Search Book");

        searchPanel.add(new JLabel("Search by Title:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        inputPanel.add(searchPanel);

        // Menambahkan panel input ke bagian atas jendela
        add(inputPanel, BorderLayout.NORTH);

        // Menambahkan event listener untuk tombol Add Book
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String genreOrSubject = genreOrSubjectField.getText();
                String type = (String) typeComboBox.getSelectedItem();

                // Validasi input pengguna
                if (title.isEmpty() || author.isEmpty() || genreOrSubject.isEmpty() || type == null) {
                    JOptionPane.showMessageDialog(LibraryFrame.this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Membuat objek buku berdasarkan tipe dan menambahkannya ke perpustakaan
                if (type.equals("Fiction")) {
                    library.addBook(new FictionBook(title, author, genreOrSubject));
                } else {
                    library.addBook(new NonFictionBook(title, author, genreOrSubject));
                }

                // Menampilkan informasi buku yang ditambahkan ke area teks
                displayArea.append("Added: " + title + " by " + author + "\n");

                // Membersihkan field input
                titleField.setText("");
                authorField.setText("");
                genreOrSubjectField.setText("");
                typeComboBox.setSelectedIndex(0);
            }
        });

        // Menambahkan event listener untuk tombol Search Book
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = searchField.getText();

                // Validasi input pencarian
                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(LibraryFrame.this, "Please enter a title to search", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Mencari buku berdasarkan judul
                Book book = library.findBook(title);
                if (book != null) {
                    displayArea.append("Found: " + book.getDetails() + "\n");
                } else {
                    displayArea.append("Book not found: " + title + "\n");
                }

                // Membersihkan field pencarian
                searchField.setText("");
            }
        });

        // Menampilkan jendela
        setVisible(true);
    }

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