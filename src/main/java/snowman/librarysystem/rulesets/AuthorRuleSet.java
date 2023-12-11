package snowman.librarysystem.rulesets;

import snowman.librarysystem.dialogs.AddAuthorDialog;

import java.awt.*;


public class AuthorRuleSet implements RuleSet {

    AddAuthorDialog dialog;

    @Override
    public void applyRules(Component component) throws RuleException {
        dialog = (AddAuthorDialog) component;
        nonEmptyRule();
    }

    private void nonEmptyRule() throws RuleException {
        if (dialog.getFirstName().trim().isEmpty()) {
            throw new RuleException("First name should not be empty!");
        }
        if (dialog.getLastName().trim().isEmpty()) {
            throw new RuleException("Last name should not be empty!");
        }

        if (dialog.getPhone().trim().isEmpty()) {
            throw new RuleException("Phone  should not be empty!");
        }
        if (dialog.getState().trim().isEmpty()) {
            throw new RuleException("State should not be empty!");
        }
        if (dialog.getCity().trim().isEmpty()) {
            throw new RuleException("City name should not be empty!");
        }
        if (dialog.getStreet().trim().isEmpty()) {
            throw new RuleException("Street not be empty!");
        }

        if (dialog.getZip().trim().isEmpty()) {
            throw new RuleException("Zip code name should not be empty!");
        }

        if (dialog.getCredentials().trim().isEmpty()) {
            throw new RuleException("credentials should not be empty!");
        }
    }
}
