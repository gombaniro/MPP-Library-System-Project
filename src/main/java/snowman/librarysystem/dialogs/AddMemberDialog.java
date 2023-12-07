package snowman.librarysystem.dialogs;


import snowman.librarysystem.eventHandlers.DialogClosingListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AddMemberDialog extends Dialog {
    JTextField firstNameInput;
    JTextField lastNameInput;
    JTextField phoneInput;
    JTextField stateInput;
    JTextField cityInput;
    JTextField streetInput;

    JButton cancelButton;
    JButton confirmButton;

    public AddMemberDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        setSize(300, 300);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2));
        formPanel.add(new JLabel("First name: "));

        firstNameInput = new JTextField(" ");
        formPanel.add(firstNameInput);
        formPanel.add(new JLabel("Last name: "));
        lastNameInput = new JTextField("");
        formPanel.add(lastNameInput);
        formPanel.add(new JLabel("Phone: "));
        phoneInput = new JTextField("");
        formPanel.add(phoneInput);

        formPanel.add(new JLabel("State: "));
        stateInput = new JTextField(" ");
        formPanel.add(stateInput);
        formPanel.add(new JLabel("City: "));
        cityInput = new JTextField("");
        formPanel.add(cityInput);
        formPanel.add(new JLabel("Street: "));
        streetInput = new JTextField("");
        formPanel.add(streetInput);

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
