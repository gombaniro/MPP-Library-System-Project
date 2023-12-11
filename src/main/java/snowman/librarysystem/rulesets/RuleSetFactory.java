package snowman.librarysystem.rulesets;

import snowman.librarysystem.dialogs.AddAuthorDialog;
import snowman.librarysystem.dialogs.AddMemberDialog;
import snowman.librarysystem.dialogs.AddNewBookDialog;

import java.awt.*;
import java.util.HashMap;

final public class RuleSetFactory {
    static HashMap<Class<? extends Component>, RuleSet> map = new HashMap<>();

    static {
        map.put(AddMemberDialog.class, new MemberRuleSet());
        map.put(AddAuthorDialog.class, new AuthorRuleSet());
        map.put(AddNewBookDialog.class, new BookRuleSet());

    }

    private RuleSetFactory() {
    }

    public static RuleSet getRuleSet(Component c) {
        Class<? extends Component> cl = c.getClass();
        if (!map.containsKey(cl)) {
            throw new IllegalArgumentException(
                    "No RuleSet found for this Component");
        }
        return map.get(cl);
    }

}
