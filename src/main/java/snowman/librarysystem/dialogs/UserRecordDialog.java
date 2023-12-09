package snowman.librarysystem.dialogs;


import snowman.business.Book;
import snowman.business.BookCopy;
import snowman.business.CheckoutRecordEntry;
import snowman.business.LibraryMember;
import snowman.dataaccess.DataAccess;
import snowman.dataaccess.DataAccessFacade;
import snowman.librarysystem.eventHandlers.DialogClosingListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserRecordDialog extends Dialog {
    private JTextField userIdField;
    private JButton searchButton;
    private JTable recordEntryTable;

    public UserRecordDialog(JFrame owner, String title, boolean modal) {
        super(owner, "User Record Entry", true);
        userIdField = new JTextField(10);

        searchButton = new JButton("Search");
        searchButton.addActionListener(event -> {
            DataAccess da = new DataAccessFacade();
            HashMap<String, LibraryMember> membersMap = da.readMemberMap();

            String userId = userIdField.getText();
            LibraryMember user = membersMap.get(userId);
            if (user != null) {
                recordEntryTable.setModel(
                    new MemberRecordEntriesModel(
                        user.getCheckoutRecord().getRecordEntryList()
                    )
                );
            }
        });

        setLayout(new BorderLayout());
        add(userIdField, BorderLayout.NORTH);
        add(searchButton, BorderLayout.CENTER);
        recordEntryTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(recordEntryTable);
        add(scrollPane, BorderLayout.SOUTH);

        pack();

        addWindowListener(new DialogClosingListener(this));
    }
}