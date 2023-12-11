package snowman.librarysystem.rulesets;

import snowman.librarysystem.dialogs.AddNewBookDialog;

import java.awt.*;


public class BookRuleSet implements RuleSet {

    AddNewBookDialog dialog;

    @Override
    public void applyRules(Component component) throws RuleException {
        dialog = (AddNewBookDialog) component;
        nonEmptyRule();
        numericMaximumCheckoutLengthRule();
        numericMaximumCheckoutLength7Or21Rule();
        numericNumberOfCopiesRule();
        numericNumberOfCopiesGreaterThanZeroRule();
        atLeastOneAuthorRule();
    }

    private void nonEmptyRule() throws RuleException {
        if (dialog.getISBN().trim().isEmpty()) {
            throw new RuleException("ISBN should not be empty!");
        }
        if (dialog.getBookTitle().trim().isEmpty()) {
            throw new RuleException("Title should not be empty!");
        }

        if (dialog.getMaximumCheckoutLength().trim().isEmpty()) {
            throw new RuleException("Maximum checkout length  should not be empty!");
        }
        if (dialog.getNumberOfCopies().trim().isEmpty()) {
            throw new RuleException("Number of copies should not be empty!");
        }
    }

    private void numericMaximumCheckoutLengthRule() throws RuleException {

        try {
            Integer.parseInt(dialog.getMaximumCheckoutLength());
        } catch (NumberFormatException e) {
            throw new RuleException("Maximum Checkout Length should be a number");
        }
    }

    private void numericMaximumCheckoutLength7Or21Rule() throws RuleException {

        int num = Integer.parseInt(dialog.getMaximumCheckoutLength());
        if (!(num == 7 || num == 21)) {
            throw new RuleException("The Maximum checkout is either 7 or 21");
        }
    }

    private void numericNumberOfCopiesRule() throws RuleException {

        try {
            Integer.parseInt(dialog.getNumberOfCopies());
        } catch (NumberFormatException e) {
            throw new RuleException("Number of copies should be a number");
        }
    }

    private void numericNumberOfCopiesGreaterThanZeroRule() throws RuleException {
        int num = Integer.parseInt(dialog.getNumberOfCopies());
        if (num < 1) {
            throw new RuleException("The book copies should be at least 1");
        }

    }

    private void atLeastOneAuthorRule() throws RuleException {
        if (dialog.getAuthors() == null || dialog.getAuthors().isEmpty()) {
            throw new RuleException("At least one author should be added!");
        }
    }

}

