package snowman.librarysystem.dialogs;


import snowman.business.Address;
import snowman.business.CheckoutRecord;
import snowman.business.CheckoutRecordEntry;
import snowman.business.LibraryMember;
import snowman.dataaccess.DataAccessFacade;
import snowman.librarysystem.eventHandlers.DialogClosingListener;
import snowman.librarysystem.rulesets.RuleException;
import snowman.librarysystem.rulesets.RuleSet;
import snowman.librarysystem.rulesets.RuleSetFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddMemberDialog extends Dialog {
    JTextField firstNameInput;
    JTextField lastNameInput;
    JTextField phoneInput;
    JTextField stateInput;
    JTextField cityInput;
    JTextField streetInput;
    JTextField zipInput;

    JButton cancelButton;
    JButton confirmButton;

    public AddMemberDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        setSize(300, 320);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2));
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

        Border emptyBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        formPanel.setBorder(emptyBorder);
        add(formPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(event -> setVisible(false));
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(event -> {
            try {
                RuleSet rules = RuleSetFactory.getRuleSet(AddMemberDialog.this);
                rules.applyRules(AddMemberDialog.this);
            } catch (RuleException e) {
                JOptionPane.showMessageDialog(AddMemberDialog.this, e.getMessage());
                return;
            }


            DataAccessFacade accessFacade = new DataAccessFacade();
            HashMap<String, LibraryMember> keyMembers = accessFacade.readMemberMap();

            long largestMemberId = 0;
            for (Map.Entry<String, LibraryMember> entry : keyMembers.entrySet()) {
                LibraryMember member = entry.getValue();
                long id = Long.parseLong(member.getMemberId());
                if (id > largestMemberId) {
                    largestMemberId = id;
                }
            }


            Address address = new Address(getStreet(), getCity(), getState(), getZip());
            LibraryMember newMember = new LibraryMember(
                    String.valueOf(++largestMemberId),
                    getFirstName(),
                    getLastName(),
                    getPhone(),
                    address,
                    new CheckoutRecord(new ArrayList<CheckoutRecordEntry>()));

            for (Map.Entry<String, LibraryMember> entry : keyMembers.entrySet()) {
                LibraryMember member = entry.getValue();
                if (member.equals(newMember)) {
                    JOptionPane.showMessageDialog(owner, String.format("Your Member exists with ID: %s", member.getMemberId()));
                    this.setVisible(false);
                    return;
                }
            }


            accessFacade.saveNewMember(newMember);
            JOptionPane.showMessageDialog(owner, String.format("Your Member ID is: %s", newMember.getMemberId()));
            clearInputs();
            this.setVisible(false);

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


}
