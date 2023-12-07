package snowman.librarysystem.dialogs;


import snowman.librarysystem.eventHandlers.DialogClosingListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AddBookCopyDialog extends Dialog {
    JTextField searchInput;
    JButton searchButton;
    JTable bookTable;

    public AddBookCopyDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        setSize(400, 300);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1, 3));
        searchInput = new JTextField("");
        searchButton = new JButton("search");
        searchButton.addActionListener(event -> setVisible(false));
        searchPanel.add(new JLabel("ISBN: "));
        searchPanel.add(searchInput);
        searchPanel.add(searchButton);
        Border lineBorder = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(lineBorder, "Find a book By ISBN");
        searchPanel.setBorder(titled);
        add(searchPanel, BorderLayout.NORTH);
        JPanel tablePanel = new JPanel();
        tablePanel.setSize(300, 200); // to be removed
        tablePanel.setBackground(Color.GREEN);  // to be removed
        tablePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));  // to be removed
        add(tablePanel, BorderLayout.CENTER);
        setLocationRelativeTo(owner);
        pack();

        addWindowListener(new DialogClosingListener(this));

    }
}