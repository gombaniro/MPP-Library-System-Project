package snowman.librarysystem.rulesets;


import java.awt.Component;

public interface RuleSet {
    void applyRules(Component ob) throws RuleException;
}
