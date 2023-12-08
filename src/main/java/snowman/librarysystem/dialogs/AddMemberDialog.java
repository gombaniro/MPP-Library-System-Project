package snowman.librarysystem.dialogs;


import snowman.business.Address;
import snowman.business.LibraryMember;
import snowman.dataaccess.DataAccessFacade;
import snowman.librarysystem.eventHandlers.DialogClosingListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        // TODO: add validation rule sets and  and refactor me out
        confirmButton.addActionListener(event -> {
             if (getFirstName().isEmpty() ||
                 getLastName().isEmpty() ||
                 getPhone().isEmpty() ||
                 getState().isEmpty() ||
                 getCity().isEmpty() ||
                 getStreet().isEmpty()) {
                 JOptionPane.showMessageDialog(owner, "Invalid data");

             } else {
                 DataAccessFacade accessFacade = new DataAccessFacade();
                 HashMap<String, LibraryMember> keyMembers = accessFacade.readMemberMap();

                 long largestMemberId = 0;
                 for(Map.Entry<String, LibraryMember> entry: keyMembers.entrySet()) {
                     System.out.println(entry.getValue());
                     LibraryMember member = entry.getValue();
                       long id =  Long.parseLong(member.getMemberId());
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
                         address
                 );
                 accessFacade.saveNewMember(newMember);
                 JOptionPane.showMessageDialog(owner, String.format("Your Member ID is: %s", newMember.getMemberId()));

             }
        });
        actionPanel.add(cancelButton);
        actionPanel.add(confirmButton);

        addWindowListener(new DialogClosingListener(this));
        add(actionPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(owner);
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
