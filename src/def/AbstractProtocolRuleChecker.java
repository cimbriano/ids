package def;

import net.sourceforge.jpcap.net.*;
import java.util.regex.*;
import java.util.List;

public abstract class AbstractProtocolRuleChecker<T extends IPPacket, S extends AbstractRule> {

    public abstract void add(T packet, S rule, String rulename, String host);

    protected boolean checkContents(IPPacket packet, ProtocolSubrule subRule) {
	String data = new String(packet.getData());

	Pattern p = Pattern.compile(subRule.regexp);
	Matcher m = p.matcher(data);

	return m.find();
    }

  protected void alert(String rulename, List<T> matchedPackets){
    System.out.println("Alert!! Matched rule '" + rulename + "'");
    
    for(T packet : matchedPackets){
        System.out.println(packet.toColoredString(true));
    }
    
    System.out.println("*******************");
    System.out.print("\n");
    
  }

}