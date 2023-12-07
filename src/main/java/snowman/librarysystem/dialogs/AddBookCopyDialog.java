package snowman.librarysystem.dialogs;


import snowman.librarysystem.eventHandlers.DialogClosingListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AddBookCopyDialog extends Dialog {
    JTextField searchInput;
    JButton searchButton;
    JButton copyButton;
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

        JPanel tableContainer = new JPanel();
        tableContainer.setLayout(new BoxLayout(tableContainer, BoxLayout.Y_AXIS));
        JPanel addCopyPanel = new JPanel();
        addCopyPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        copyButton = new JButton("New Copy");
        addCopyPanel.add(copyButton);
        tableContainer.add(addCopyPanel);

        JPanel tablePanel = new JPanel();
        addTable(tablePanel);
        tableContainer.add(tablePanel);

         tablePanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 30));  // to be removed
        add(tableContainer, BorderLayout.CENTER);
        setLocationRelativeTo(owner);
        pack();

        addWindowListener(new DialogClosingListener(this));

    }

    void addTable(JPanel panel) {
        String[] colHeads = {"ISBN", "Title", "Copy ID#"};

        // Initialize data.
        Object[][] data = {
                { "1-45687","Java Programming", "12"},
                { "1-45687","Java Programming", "11"},
                { "1-45687","Java Programming", "10"},
                { "1-45687","Java Programming", "9"},
                { "1-45687","Java Programming", "8"},
                { "1-45687","Java Programming", "7"},
                { "1-45687","Java Programming", "6"},
                { "1-45687","Java Programming", "5"},
                { "1-45687","Java Programming", "4"},
                { "1-45687","Java Programming", "3"},
                { "1-45687","Java Programming", "2"},
                { "1-45687","Java Programming", "1"},
        };

        JTable table = new JTable(data, colHeads);

        JScrollPane jScrollPane = new JScrollPane(table);
        panel.add(jScrollPane);
    }
}