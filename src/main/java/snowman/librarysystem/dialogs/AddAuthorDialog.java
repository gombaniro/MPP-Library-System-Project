package snowman.librarysystem.dialogs;

import snowman.business.Address;
import snowman.business.Author;
import snowman.librarysystem.eventHandlers.DialogClosingListener;
import snowman.librarysystem.rulesets.RuleException;
import snowman.librarysystem.rulesets.RuleSet;
import snowman.librarysystem.rulesets.RuleSetFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;


public class AddAuthorDialog extends Dialog {
    JTextField firstNameInput;
    JTextField lastNameInput;
    JTextField phoneInput;
    JTextField stateInput;
    JTextField cityInput;
    JTextField streetInput;
    JTextField zipInput;
    JTextArea credentialsInput;

    JButton cancelButton;
    JButton confirmButton;

    List<Author> authors;

    public AddAuthorDialog(AddNewBookDialog owner, String title, boolean modal, List<Author> authors) {
        super(owner, title, modal);
        setSize(400, 400);
        this.authors = authors;
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2));
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

        formPanel.add(new JLabel("Zip: "));
        zipInput = new JTextField("");
        formPanel.add(zipInput);

        formPanel.add(new JLabel("credentials: "));
        credentialsInput = new JTextArea(8, 20);
        formPanel.add(credentialsInput);

        Border emptyBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        formPanel.setBorder(emptyBorder);
        add(formPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(event -> setVisible(false));
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(event -> {
            try {
                RuleSet rules = RuleSetFactory.getRuleSet(AddAuthorDialog.this);
                rules.applyRules(AddAuthorDialog.this);
            } catch (RuleException e) {
                JOptionPane.showMessageDialog(AddAuthorDialog.this, e.getMessage());
                return;
            }

            Address address = new Address(getStreet(), getCity(), getState(), getZip());
            Author author = new Author(getFirstName(), getLastName(), getPhone(), address, getCredentials());

            authors.add(author);
            clearInputs();
            setVisible(false);
            owner.updateAuthorLabelText();
        });
        actionPanel.add(cancelButton);
        actionPanel.add(confirmButton);

        addWindowListener(new DialogClosingListener(this));
        add(actionPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(owner);
    }

    private void clearInputs() {
        firstNameInput.setText("");
        lastNameInput.setText("");
        phoneInput.setText("");
        stateInput.setText("");
        cityInput.setText("");
        streetInput.setText("");
        zipInput.setText("");
        credentialsInput.setText("");
    }

    public String getFirstName() {
        return firstNameInput.getText();
    }

    public String getLastName() {
        return lastNameInput.getText();
    }

    public String getPhone() {
        return phoneInput.getText();
    }

    public String getState() {
        return stateInput.getText();
    }

    public String getCity() {
        return cityInput.getText();
    }

    public String getStreet() {
        return streetInput.getText();
    }

    public String getZip() {
        return zipInput.getText();
    }

    public String getCredentials() {
        return credentialsInput.getText();
    }


}

