package def;

import net.sourceforge.jpcap.net.*;
import stream.*;

public class Rule
{
    final String name;
    final AbstractRule contents;

    public Rule(String n, AbstractRule c) {
	name = n;
	contents = c;
    }

    public void scan(IPPacket packet, ThreatDefinition threat) {
	contents.scan(packet, this, threat);
    }    

    /*
     *
     */

    public void printRule() {
	System.out.print("name="+name+"\n");
	contents.printRule();
    }

}
