package snowman.librarysystem.dialogs;

import snowman.business.Author;
import snowman.librarysystem.eventHandlers.DialogClosingListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AddNewBookDialog extends Dialog {
    JTextField ISBNInput;
    JTextField titleInput;
    JTextField maximumCheckoutLengthInput;
    JTextField numberOfCopiesInput;
    List<Author> authors;


    JButton cancelButton;
    JButton confirmButton;

    public AddNewBookDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        setSize(300, 320);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2));
        formPanel.add(new JLabel("ISBN: "));

        ISBNInput = new JTextField(" ");
        formPanel.add(ISBNInput);
        formPanel.add(new JLabel("Title: "));
        titleInput = new JTextField("");
        formPanel.add(titleInput);
        formPanel.add(new JLabel("Maximum Checkout Length: "));
        maximumCheckoutLengthInput = new JTextField("");
        formPanel.add(maximumCheckoutLengthInput);

        formPanel.add(new JLabel("Number Of Copies: "));
        numberOfCopiesInput = new JTextField(" ");
        formPanel.add(numberOfCopiesInput);


        Border emptyBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        formPanel.setBorder(emptyBorder);
        add(formPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(event -> setVisible(false));
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(event -> {
        });
        actionPanel.add(cancelButton);
        actionPanel.add(confirmButton);

        addWindowListener(new DialogClosingListener(this));
        add(actionPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(owner);
    }

    private void clearInputs() {
        ISBNInput.setText("");
        titleInput.setText("");
        maximumCheckoutLengthInput.setText("");
        numberOfCopiesInput.setText("");
        authors = new ArrayList<Author>();
    }

    public String getISBN() {
        return ISBNInput.getText();
    }

    public String getBookTitle() {
        return titleInput.getText();
    }

    public String getMaximumCheckoutLength() {
        return maximumCheckoutLengthInput.getText();
    }

    public String getNumberOfCopies() {
        return numberOfCopiesInput.getText();
    }

    public List<Author> getAuthors() {
        return Collections.unmodifiableList(authors);
    }

}

