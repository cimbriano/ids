package def;

import java.util.*;

public class ThreatDefinition implements Iterable<Rule>
{
    final RuleList rules;
    final String ip;

    public ThreatDefinition(String ip, RuleList l) {
	this.ip = ip;	
	rules = l;
    }

    public Iterator<Rule> iterator() {
	return rules.iterator();
    }


    /*
     *
     */


    public void print() {
	System.out.print("host="+ip+"\n\n");

	for (Rule r : rules)
	    r.printRule();
    }

}