package def;

import java.util.*;

/**
 * RuleList.java: A wrapper class for lists of Rules.
 * java_cup has difficult with generics so we had to introduce
 * a wrapper class to make lists of rules during parsing.
 */
public class RuleList extends ArrayList<Rule> {

    public RuleList(Rule r) {
	super();
	add(r);
    }

}