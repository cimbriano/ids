package ids;

public class Rule
{
    final String name;
    final AbstractRule contents;

    public Rule(String n, AbstractRule c) {
	name = n;
	contents = c;
    }

    void printRule() {
	System.out.print("name="+name+"\n");
	contents.printRule();
    }
}
