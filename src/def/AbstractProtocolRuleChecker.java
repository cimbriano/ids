package def;

import net.sourceforge.jpcap.net.*;
import java.util.regex.*;

public abstract class AbstractProtocolRuleChecker<T extends IPPacket, S extends AbstractRule> {

    public abstract void add(T packet, S rule, String rulename, String host);

    protected boolean checkContents(IPPacket packet, ProtocolSubrule subRule) {
	String data = new String(packet.getData());

	Pattern p = Pattern.compile(subRule.regexp);
	Matcher m = p.matcher(data);

	return m.find();
    }

}