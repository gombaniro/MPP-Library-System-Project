package snowman.librarysystem.dialogs;


import snowman.librarysystem.eventHandlers.DialogClosingListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CheckoutDialog extends Dialog {
//    JTextField bookISBN;
    JTextField memberIDField;

    JLabel memberIDLabel;

    JButton clearButton;
    JButton searchButton;

    public CheckoutDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        setSize(300, 300);
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 6));
        memberIDLabel =  new JLabel("memberID: ");
        formPanel.add(memberIDLabel);
        memberIDField = new JTextField(20);
        formPanel.add(memberIDField);
        Border emptyBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        formPanel.setBorder(emptyBorder);
        add(formPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        clearButton = new JButton("Clear");
        clearButton.addActionListener(event -> memberIDField.setText(""));
        searchButton = new JButton("Search");
        searchButton.addActionListener(event -> setVisible(false));
        actionPanel.add(clearButton);
        actionPanel.add(searchButton);

        addWindowListener(new DialogClosingListener(this));
        add(actionPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(owner);
    }


}
