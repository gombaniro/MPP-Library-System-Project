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

public class AddBookCopyDialog extends Dialog {
    JTextField searchInput;
    JButton searchButton;
    JButton copyButton;
    Book searchResult;
    JPanel tablePanel;
    JPanel tableContainer;
    JTable table;


    public AddBookCopyDialog(JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        setSize(400, 300);
        buildUI(owner);

    }

    private void buildUI(JFrame owner) {

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1, 3));
        searchInput = new JTextField("");
        searchButton = new JButton("search");

        searchButton.addActionListener(event -> {
            DataAccessFacade accessFacade = new DataAccessFacade();
            if (getSearchISBN().isEmpty()) {
                JOptionPane.showMessageDialog(owner, "Please enter ISBN and search key");
                return;
            }

            HashMap<String, Book> books = accessFacade.readBooksMap();
            for(Map.Entry<String, Book> item: books.entrySet()) {
                System.out.println(item.getValue());
                if (item.getValue().getIsbn().equals(getSearchISBN())) {
                    searchResult = item.getValue();
                }
            }

            if (searchResult == null) {
                JOptionPane.showMessageDialog(owner, "There is no such book");
                return;
            }
            System.out.println("Search result:");
            System.out.println(searchResult);

            System.out.println("Search result copy:");
            System.out.println(Arrays.toString(searchResult.getCopies()));

            buildUI(owner); // find a better solution instead of rebuild the entire UI
        });
        searchPanel.add(new JLabel("ISBN: "));
        searchPanel.add(searchInput);
        searchPanel.add(searchButton);
        Border lineBorder = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(lineBorder, "Find a book By ISBN");
        searchPanel.setBorder(titled);
        add(searchPanel, BorderLayout.NORTH);

        tableContainer = new JPanel();
        tableContainer.setLayout(new BoxLayout(tableContainer, BoxLayout.Y_AXIS));
        JPanel addCopyPanel = new JPanel();
        addCopyPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        copyButton = new JButton("New Copy");
        copyButton.addActionListener((event) -> {
            if (searchResult == null) {
                JOptionPane.showMessageDialog(owner, "Cannot copy empty book");
                return;
            }
            searchResult.addCopy();
            DataAccessFacade accessFacade = new DataAccessFacade();
            HashMap<String, Book> books = accessFacade.readBooksMap();
            accessFacade.saveNewCopy(books);
            buildUI(owner);

        });
        addCopyPanel.add(copyButton);
        tableContainer.add(addCopyPanel);

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
         table = new JTable(new BookCopyModel(book));
         JScrollPane jScrollPane = new JScrollPane(table);
         panel.add(jScrollPane);
    }

    public String getSearchISBN() {
        return searchInput.getText();
    }
}