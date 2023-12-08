package snowman.librarysystem.rulesets;

import snowman.librarysystem.dialogs.AddBookCopyDialog;
import snowman.librarysystem.dialogs.AddMemberDialog;

import java.awt.*;

public class MemberRuleSet implements RuleSet {

    AddMemberDialog dialog;
    @Override
    public void applyRules(Component component) throws RuleException {
        dialog = (AddMemberDialog) component;
        nonEmptyRule();
    }

    private void nonEmptyRule() throws RuleException {
        if(dialog.getFirstName().trim().isEmpty()) {
            throw new RuleException("First name should not be empty!");
        }
        if(dialog.getLastName().trim().isEmpty()) {
            throw new RuleException("Last name should not be empty!");
        }

        if(dialog.getPhone().trim().isEmpty()) {
            throw new RuleException("Phone  should not be empty!");
        }
        if(dialog.getState().trim().isEmpty()) {
            throw new RuleException("State should not be empty!");
        }
        if(dialog.getCity().trim().isEmpty()) {
            throw new RuleException("City name should not be empty!");
        }
        if(dialog.getStreet().trim().isEmpty()) {
            throw new RuleException("Street not be empty!");
        }

        if(dialog.getZip().trim().isEmpty()) {
            throw new RuleException("Zip code name should not be empty!");
        }
    }
}
