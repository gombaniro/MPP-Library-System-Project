package snowman.librarysystem.dialogs;


import snowman.business.Book;
import snowman.business.CheckoutRecord;
import snowman.business.CheckoutRecordEntry;
import snowman.business.LibraryMember;
import snowman.dataaccess.DataAccessFacade;
import snowman.librarysystem.eventHandlers.DialogClosingListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutRecordDialog extends Dialog {
    JTextField searchInput;
    JButton searchButton;
//    JButton copyButton;
//    Book searchResult;
    ArrayList<CheckoutRecordEntry> searchResult;
//    CheckoutRecord searchResult;
    LibraryMember member;
//    Book searchResult;
    JPanel tablePanel;
    JPanel tableContainer;
    JTable table;


    public CheckoutRecordDialog(JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        setSize(400, 300);
        buildUI(owner);

    }

    private void buildUI(JFrame owner) {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1, 3));
        searchInput = new JTextField("");
        if (searchResult != null) {
            searchInput.setText(member.getMemberId());
        } else {
            searchInput.setText("");
        }

        searchButton = new JButton("search");

        searchButton.addActionListener(event -> {
            DataAccessFacade accessFacade = new DataAccessFacade();
            HashMap<String, LibraryMember> membersMap = accessFacade.readMemberMap();
            LibraryMember member = membersMap.get(searchInput.getText());
            if(member == null){
                JOptionPane.showMessageDialog(owner, "There is no such member");
                return;
            }

            if(member.getCheckoutRecord() == null){
                JOptionPane.showMessageDialog(owner, "Current member has no checkout record");
                return;
            }
//            HashMap<String, Book> books = accessFacade.readBooksMap();
//            for(Map.Entry<String, Book> item: books.entrySet()) {
//                System.out.println(item.getValue());
//                if (item.getValue().getIsbn().equals(getSearchISBN())) {
//                    searchResult = item.getValue();
//                }
//            }
            List<CheckoutRecordEntry> searchResult = member.getCheckoutRecord().getRecordEntryList();
            if(searchResult == null){
                JOptionPane.showMessageDialog(owner, "Current member has no checkout record");
                return;
            }

            buildUI(owner); // find a better solution instead of rebuild the entire UI
        });
        searchPanel.add(new JLabel("Librarian Member ID: "));
        searchPanel.add(searchInput);
        searchPanel.add(searchButton);
        Border lineBorder = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(lineBorder, "Find CheckoutRecord");
        searchPanel.setBorder(titled);
        add(searchPanel, BorderLayout.NORTH);

        tableContainer = new JPanel();
        tableContainer.setLayout(new BoxLayout(tableContainer, BoxLayout.Y_AXIS));
        JPanel addCopyPanel = new JPanel();
        addCopyPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
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

    void addTable(JPanel panel, List<CheckoutRecordEntry> entries) {
         table = new JTable(new CheckoutRecordModel(entries));
         JScrollPane jScrollPane = new JScrollPane(table);
         panel.add(jScrollPane);
    }

    public String getSearchMemberID() {
        return searchInput.getText();
    }
}