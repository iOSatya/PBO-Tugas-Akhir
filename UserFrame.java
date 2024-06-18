import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends JFrame {
    private Library library;
    private JTextArea displayArea;

    public UserFrame() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        library = new Library();
        setTitle("Library Management System - User");
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

        
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Book"));

        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Search Book");

        searchPanel.add(new JLabel("Search by Title:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        inputPanel.add(searchPanel);

        
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
                displayArea.setText(allBooks); 
            }
        });
        
        
        add(inputPanel, BorderLayout.NORTH);

        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = searchField.getText();

                
                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(UserFrame.this, "Please enter a title to search", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Book book = library.findBook(title);
                if (book != null) {
                    displayArea.append("Found: " + book.getDetails() + "\n");
                } else {
                    displayArea.append("Book not found: " + title + "\n");
                }

                searchField.setText("");
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