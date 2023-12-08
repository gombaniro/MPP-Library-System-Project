package snowman.librarysystem.rulesets;

import snowman.librarysystem.dialogs.AddBookCopyDialog;
import snowman.librarysystem.dialogs.AddMemberDialog;

import java.awt.*;
import java.util.HashMap;

final public class RuleSetFactory {
    private RuleSetFactory(){}
    static HashMap<Class<? extends Component>, RuleSet> map = new HashMap<>();
    static {
        map.put(AddMemberDialog.class, new MemberRuleSet());

    }

    public static RuleSet getRuleSet(Component c) {
        Class<? extends Component> cl = c.getClass();
        if(!map.containsKey(cl)) {
            throw new IllegalArgumentException(
                    "No RuleSet found for this Component");
        }
        return map.get(cl);
    }

}
