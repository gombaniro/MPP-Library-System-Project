package snowman.librarysystem.dialogs;


import snowman.business.CheckoutRecordEntry;
import snowman.business.LibraryMember;
import snowman.dataaccess.DataAccessFacade;
import snowman.librarysystem.eventHandlers.DialogClosingListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;


public class CheckoutRecordDialog extends Dialog {
    JTextField searchInputField;
    JButton searchButton;
    ArrayList<CheckoutRecordEntry> searchResult;
    LibraryMember member;
    JPanel tablePanel;
    //    JPanel table;
    JTable table;

    JLabel memberInfoLabel;

    DataAccessFacade facade = new DataAccessFacade();

    HashMap<String, LibraryMember> membersMap = facade.readMemberMap();

    public CheckoutRecordDialog(JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        setSize(400, 300);
        buildUI(owner);
    }

    private void buildUI(JFrame owner) {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1, 3));
        searchInputField = new JTextField(10);
        searchButton = new JButton("search");
        searchButton.addActionListener((ActionEvent e) -> {
            search(searchInputField.getText());
        });

        searchPanel.add(new JLabel("Librarian Member ID: "));
        searchPanel.add(searchInputField);
        searchPanel.add(searchButton);
        memberInfoLabel = new JLabel();
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        add(searchPanel, BorderLayout.NORTH);

        tablePanel = new JPanel(new BorderLayout());
        table = new JTable(new CheckoutRecordModel(searchResult));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrollPane = new JScrollPane(table);

        tablePanel.add(jScrollPane, BorderLayout.CENTER);
        tablePanel.add(memberInfoLabel, BorderLayout.NORTH);
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));  // to be removed
        add(tablePanel, BorderLayout.CENTER);
        setLocationRelativeTo(owner);
        addWindowListener(new DialogClosingListener(this));
        setBounds(100, 100, 1000, 600);
        setLocationRelativeTo(null);
    }

    public void search(String memberID) {
        if (searchInputField == null || memberID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "input should not be empty", "No empty Check", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (membersMap == null) {
            membersMap = facade.readMemberMap();
        }
        LibraryMember member = membersMap.get(memberID);
        if (member == null) {
            JOptionPane.showMessageDialog(null, "There is no such member", "Search Result", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        if (member.getCheckoutRecord() == null) {
            JOptionPane.showMessageDialog(null, "Current member has no checkout record", "Search Result", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        searchResult = (ArrayList<CheckoutRecordEntry>) member.getCheckoutRecord().getRecordEntryList();
        if (searchResult == null) {
            JOptionPane.showMessageDialog(null, "Current member has no checkout record", "Search Result", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        memberInfoLabel.setText(member.toString());
        memberInfoLabel.setFont(new Font("MV Boli", Font.BOLD, 15));
        memberInfoLabel.setForeground(Color.GREEN);
        memberInfoLabel.setBackground(Color.BLACK);
        memberInfoLabel.setOpaque(true);
        memberInfoLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        // Update the table model with the new search result
        CheckoutRecordModel model = (CheckoutRecordModel) table.getModel();
        model.setCheckoutRecordEntries(searchResult);

        // Notify the table that the model has changed
        model.fireTableDataChanged();
        System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", "Title", "ISBN", "Copy Number", "Checkout Date", "Return Date");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", "-----------", "----------", "----------", "----------", "----------");
        for (CheckoutRecordEntry entry : searchResult) {
            System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                    entry.getBookCopy().getTitle(),
                    entry.getBookCopy().getBookISBN(),
                    entry.getBookCopy().getCopyNum(),
                    entry.getCheckoutDate(),
                    entry.getDueDate());
        }
    }
}