package ids;


public class ThreatDefinition
{
    final AbstractRuleList rules;
    final String ip;

    public ThreatDefinition(String a, AbstractRuleList l) {
	ip = new String(a);
	
	rules = l;
    }

    public void print() {
	System.out.print("host="+ip+"\n\n");

	for (AbstractRule r : rules)
	    r.printRule();
    }

}