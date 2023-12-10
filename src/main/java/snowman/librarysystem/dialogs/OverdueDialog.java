package snowman.librarysystem.dialogs;


import snowman.business.Book;
import snowman.business.BookCopy;
import snowman.dataaccess.DataAccessFacade;
import snowman.librarysystem.eventHandlers.DialogClosingListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OverdueDialog extends Dialog {

    JTextField searchInput;
    JButton searchButton;
    Book searchResult;
    JPanel tablePanel;
    JPanel tableContainer;
    JTable table;

    public OverdueDialog(JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        setSize(400, 300);
        buildUI(owner);
    }

    private void buildUI(JFrame owner) {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1, 3));
        searchInput = new JTextField("");
        if (searchResult != null) {
            searchInput.setText(searchResult.getIsbn());
        } else {
            searchInput.setText("");
        }

        searchButton = new JButton("search");

        searchButton.addActionListener(event -> {
            DataAccessFacade accessFacade = new DataAccessFacade();
            if (getSearchISBN().isEmpty()) {
                JOptionPane.showMessageDialog(owner, "Please enter ISBN and search key");
                return;
            }

            HashMap<String, Book> books = accessFacade.readBooksMap();
            for (Map.Entry<String, Book> item : books.entrySet()) {
                if (item.getValue().getIsbn().equals(getSearchISBN())) {
                    searchResult = item.getValue();
                }
            }

            if (searchResult == null) {
                JOptionPane.showMessageDialog(owner, "There is no such book");
                return;
            }

            buildUI(owner); // find a better solution instead of rebuild the entire UI
        });

        searchPanel.add(new JLabel("ISBN: "));
        searchPanel.add(searchInput);
        searchPanel.add(searchButton);
        searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Find a book By ISBN"));

        add(searchPanel, BorderLayout.NORTH);

        tableContainer = new JPanel();
        tableContainer.setLayout(new BoxLayout(tableContainer, BoxLayout.Y_AXIS));


        tablePanel = new JPanel();
        addTable(tablePanel, searchResult);
        tableContainer.add(tablePanel);

        tablePanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 30));  // to be removed
        add(tableContainer, BorderLayout.CENTER);
        setLocationRelativeTo(owner);
        addWindowListener(new DialogClosingListener(this));
        pack();
    }

    void addTable(JPanel panel, Book book) {
        table = new JTable(new OverdueModel(book));
        JScrollPane jScrollPane = new JScrollPane(table);
        panel.add(jScrollPane);
    }

    public String getSearchISBN() {
        return searchInput.getText();
    }
}