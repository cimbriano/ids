package def;

import java.util.*;

/*
 * ThreatDefinition.java: Corresponds to the top-level non-terminal
 * of the rule file. Contains the host ip and the list of rules.
 * Provides an iterator to iterate over the rules.
 */
public class ThreatDefinition implements Iterable<Rule> {
    final RuleList rules;
    final String host;

    public ThreatDefinition(String host, RuleList l) {
	this.host = host;	
	rules = l;
    }

    public Iterator<Rule> iterator() {
	return rules.iterator();
    }

    /*
     *
     */

    public void print() {
	System.out.print("host="+host+"\n\n");

	for (Rule r : rules)
	    r.printRule();
    }

}