import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Kelas untuk antarmuka grafis
public class LibraryFrame extends JFrame {
    private Library library;
    private JTextArea displayArea;

    public LibraryFrame() {
        library = new Library();
        setTitle("Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Area teks untuk menampilkan informasi buku
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 1));

        // Panel untuk menambah buku
        JPanel addBookPanel = new JPanel();
        addBookPanel.setLayout(new FlowLayout());

        JTextField titleField = new JTextField(10);
        JTextField authorField = new JTextField(10);
        JTextField genreOrSubjectField = new JTextField(10);
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Fiction", "Non-Fiction"});
        JButton addButton = new JButton("Add Book");

        addBookPanel.add(new JLabel("Title:"));
        addBookPanel.add(titleField);
        addBookPanel.add(new JLabel("Author:"));
        addBookPanel.add(authorField);
        addBookPanel.add(new JLabel("Genre/Subject:"));
        addBookPanel.add(genreOrSubjectField);
        addBookPanel.add(new JLabel("Type:"));
        addBookPanel.add(typeComboBox);
        addBookPanel.add(addButton);
        inputPanel.add(addBookPanel);

        // Panel untuk mencari buku
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        JTextField searchField = new JTextField(10);
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
                String searchTitle = searchField.getText();

                // Validasi input pengguna
                if (searchTitle.isEmpty()) {
                    JOptionPane.showMessageDialog(LibraryFrame.this, "Please enter a title to search", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Mencari buku berdasarkan judul
                Book book = library.findBook(searchTitle);
                if (book != null) {
                    // Menampilkan informasi buku yang ditemukan
                    displayArea.append("Found: " + book.getDetails() + "\n");
                } else {
                    // Menampilkan pesan jika buku tidak ditemukan
                    displayArea.append("Book not found: " + searchTitle + "\n");
                }

                // Membersihkan field pencarian
                searchField.setText("");
            }
        });

        // Menampilkan jendela
        setVisible(true);
    }
}