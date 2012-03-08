package ids;

public class ThreatDefinition
{
    final RuleList rules;
    final String ip;

    public ThreatDefinition(String a, RuleList l) {
	ip = new String(a);	
	rules = l;
    }

    public void print() {
	System.out.print("host="+ip+"\n\n");

	for (Rule r : rules)
	    r.printRule();
    }
}