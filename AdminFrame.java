import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {
    private Library library;
    private JTextArea displayArea;

    public AdminFrame() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        library = new Library();
        setTitle("Library Management System - Admin");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setIconImage(new ImageIcon("library_icon.png").getImage());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Serif", Font.PLAIN, 16));
        displayArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel addBookPanel = new JPanel();
        addBookPanel.setLayout(new GridBagLayout());
        addBookPanel.setBorder(BorderFactory.createTitledBorder("Add New Book"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JPanel changeAvailabilityPanel = new JPanel();
        changeAvailabilityPanel.setLayout(new FlowLayout());
        changeAvailabilityPanel.setBorder(BorderFactory.createTitledBorder("Change Availability"));
        JTextField changeTitleField = new JTextField(15);
        JComboBox<String> availabilityComboBox = new JComboBox<>(new String[]{"Available", "Unavailable"});
        JButton changeButton = new JButton("Change");

        changeAvailabilityPanel.add(new JLabel("Title:"));
        changeAvailabilityPanel.add(changeTitleField);
        changeAvailabilityPanel.add(new JLabel("Status:"));
        changeAvailabilityPanel.add(availabilityComboBox);
        changeAvailabilityPanel.add(changeButton);
        
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

        // Panel untuk menghapus buku
        JPanel removeBookPanel = new JPanel();
        removeBookPanel.setLayout(new FlowLayout());
        removeBookPanel.setBorder(BorderFactory.createTitledBorder("Remove Book"));

        JTextField removeTitleField = new JTextField(15);
        JButton removeButton = new JButton("Remove Book");

        removeBookPanel.add(new JLabel("Title:"));
        removeBookPanel.add(removeTitleField);
        removeBookPanel.add(removeButton);
        
        inputPanel.add(changeAvailabilityPanel);
        inputPanel.add(removeBookPanel);

        JPanel showAllBooksPanel = new JPanel();
        showAllBooksPanel.setLayout(new FlowLayout());
        showAllBooksPanel.setBorder(BorderFactory.createTitledBorder("Show All Books"));

        JButton showButton = new JButton("Show All");

        showAllBooksPanel.add(showButton);

        inputPanel.add(showAllBooksPanel);
        add(inputPanel, BorderLayout.NORTH);
        
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String allBooks = library.getAllBooksAsString();
                displayArea.setText(allBooks); // Atur teks area dengan daftar buku
            }
        });
        
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = changeTitleField.getText();
                String availability = (String) availabilityComboBox.getSelectedItem();

                // Validasi input
                if (title.isEmpty() || availability == null) {
                    JOptionPane.showMessageDialog(AdminFrame.this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Mengubah status ketersediaan buku
                boolean available = availability.equals("Available"); // true jika tersedia
                if (library.changeBookAvailability(title, available)) {
                    displayArea.append("Availability changed: " + title + " - " + availability + "\n");
                } else {
                    displayArea.append("Book not found: " + title + "\n");
                }

                changeTitleField.setText("");
                availabilityComboBox.setSelectedIndex(0);
            }
        });
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String genreOrSubject = genreOrSubjectField.getText();
                String type = (String) typeComboBox.getSelectedItem();

                if (title.isEmpty() || author.isEmpty() || genreOrSubject.isEmpty() || type == null) {
                    JOptionPane.showMessageDialog(AdminFrame.this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (type.equals("Fiction")) {
                    library.addBook(new FictionBook(title, author, genreOrSubject));
                } else {
                    library.addBook(new NonFictionBook(title, author, genreOrSubject));
                }

                displayArea.append("Added: " + title + " by " + author + "\n");

                titleField.setText("");
                authorField.setText("");
                genreOrSubjectField.setText("");
                typeComboBox.setSelectedIndex(0);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = removeTitleField.getText();

                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(AdminFrame.this, "Please enter a title to remove", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (library.removeBook(title)) {
                    displayArea.append("Removed: " + title + "\n");
                } else {
                    displayArea.append("Book not found: " + title + "\n");
                }

                removeTitleField.setText("");
            }
        });
        
        JPanel logoutPanel = new JPanel();
        logoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Keluar");

        logoutPanel.add(logoutButton);
        add(logoutPanel, BorderLayout.SOUTH);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame();
            }
        });

        setVisible(true);
    }
}