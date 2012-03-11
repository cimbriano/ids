package def;

import stream.*;

public class Rule
{
    final String name;
    final AbstractRule contents;

    public Rule(String n, AbstractRule c) {
	name = n;
	contents = c;
    }

    public void scan(AbstractStream stream) {
	contents.scan(stream);
    }    

    /*
     *
     */

    public void printRule() {
	System.out.print("name="+name+"\n");
	contents.printRule();
    }

}
