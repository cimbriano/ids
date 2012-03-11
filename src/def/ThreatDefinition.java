package def;

import java.util.*;

public class ThreatDefinition implements Iterable<Rule>
{
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