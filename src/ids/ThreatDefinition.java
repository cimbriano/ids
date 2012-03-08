package ids;

public class ThreatDefinition
{
    final RuleList rules;
    final String ip;

    public ThreatDefinition(String ip, RuleList l) {
	this.ip = ip;	
	rules = l;
    }

    public void print() {
	System.out.print("host="+ip+"\n\n");

	for (Rule r : rules)
	    r.printRule();
    }
}