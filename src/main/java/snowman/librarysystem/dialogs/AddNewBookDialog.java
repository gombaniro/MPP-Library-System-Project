package snowman.librarysystem.dialogs;

import snowman.business.Author;
import snowman.business.Book;
import snowman.business.BookCopy;
import snowman.dataaccess.DataAccess;
import snowman.dataaccess.DataAccessFacade;
import snowman.librarysystem.eventHandlers.AddAuthorListener;
import snowman.librarysystem.eventHandlers.DialogClosingListener;
import snowman.librarysystem.rulesets.RuleException;
import snowman.librarysystem.rulesets.RuleSet;
import snowman.librarysystem.rulesets.RuleSetFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.*;
import java.util.List;


public class AddNewBookDialog extends Dialog {
    JTextField ISBNInput;
    JTextField titleInput;
    JTextField maximumCheckoutLengthInput;
    JTextField numberOfCopiesInput;
    List<Author> authors = new ArrayList<>();
    JPanel authorsPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JLabel htmlContentLabel = new JLabel();


    JButton cancelButton;
    JButton confirmButton;


    public AddNewBookDialog(JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        setSize(300, 400);
        buildUI();

    }

    void buildUI() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2));
        formPanel.add(new JLabel("ISBN: "));

        ISBNInput = new JTextField("");
        formPanel.add(ISBNInput);
        formPanel.add(new JLabel("Title: "));
        titleInput = new JTextField("");
        formPanel.add(titleInput);
        formPanel.add(new JLabel("Maximum Checkout Length: "));
        maximumCheckoutLengthInput = new JTextField("");
        formPanel.add(maximumCheckoutLengthInput);

        formPanel.add(new JLabel("Number Of Copies: "));
        numberOfCopiesInput = new JTextField("");
        formPanel.add(numberOfCopiesInput);

        formPanel.add(new JLabel("Authors: "));
        JButton newAuthorInput = new JButton("Add author");
        newAuthorInput.addActionListener(new AddAuthorListener(new AddAuthorDialog(
                this, "Add a book author", true, authors)));
        formPanel.add(newAuthorInput);



         updateAuthorLabelText();
         authorsPanel.add(htmlContentLabel);


        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(formPanel);
        centerPanel.add(authorsPanel);

        Border emptyBorder = BorderFactory.createEmptyBorder(10, 20, 10, 20);
        centerPanel.setBorder(emptyBorder);

        add(centerPanel, BorderLayout.CENTER);
        JPanel actionPanel = new JPanel();
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(event -> setVisible(false));
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(event -> {
            try {
                RuleSet rules = RuleSetFactory.getRuleSet(AddNewBookDialog.this);
                rules.applyRules(AddNewBookDialog.this);
            } catch (RuleException e) {
                JOptionPane.showMessageDialog(AddNewBookDialog.this, e.getMessage());
                return;
            }
            DataAccess accessFacade = new DataAccessFacade();
            int maxCheckoutLen = Integer.parseInt(getMaximumCheckoutLength());
            int numOfCopies = Integer.parseInt(getNumberOfCopies());
            Book book = new Book(getISBN(),getBookTitle(), maxCheckoutLen , authors, new ArrayList<BookCopy>());
            for(int i = 1; i < numOfCopies; i++ )
                book.addCopy();
            accessFacade.saveNewBook(book);
            JOptionPane.showMessageDialog(AddNewBookDialog.this,
                    String.format("The %s book has successfully added", getBookTitle()));
            setVisible(false);

        });
        actionPanel.add(cancelButton);
        actionPanel.add(confirmButton);

        addWindowListener(new DialogClosingListener(this));
        add(actionPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(getOwner());
        pack();
    }

    public void updateAuthorLabelText() {
        StringBuilder text = new StringBuilder("<html><ol>");
        for (Author author : authors) {
            text.append(String.format("<li title=\"%s\"><b>%s %s</b></li>", author.getBio(), author.getFirstName(), author.getLastName()));
        }
        text.append("</ol></html>");

        for(Author a: authors) {
            System.out.printf("%s %s %s\n",a.getFirstName(), a.getLastName(), a.getBio());
        }
        htmlContentLabel.setText(text.toString());
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

