package snowman.librarysystem.dialogs;


import snowman.librarysystem.eventHandlers.DialogClosingListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CheckoutDialog extends Dialog {
    JTextField bookISBN;
    JTextField memberID;

    JButton cancelButton;
    JButton confirmButton;

    public CheckoutDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        setSize(300, 300);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2));
        formPanel.add(new JLabel("bookISBN: "));

        bookISBN = new JTextField(" ");
        formPanel.add(bookISBN);
        formPanel.add(new JLabel("memberID: "));
        memberID = new JTextField(" ");
        formPanel.add(memberID);
        Border emptyBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        formPanel.setBorder(emptyBorder);
        add(formPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(event -> setVisible(false));
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(event -> setVisible(false));
        actionPanel.add(cancelButton);
        actionPanel.add(confirmButton);

        addWindowListener(new DialogClosingListener(this));
        add(actionPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(owner);
    }


}
