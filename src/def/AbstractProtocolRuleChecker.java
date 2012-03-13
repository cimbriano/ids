package def;

import net.sourceforge.jpcap.net.*;
import java.util.regex.*;
import java.util.List;
import java.io.*;

public abstract class AbstractProtocolRuleChecker<T extends IPPacket, S extends AbstractRule> {

    public abstract void add(T packet, S rule, String rulename, String host);

    protected boolean checkContents(IPPacket packet, ProtocolSubrule subRule) {
	try {
	    String data = new String(packet.getData(), "ISO-8859-1");

	    Pattern p = Pattern.compile(subRule.regexp);
	    Matcher m = p.matcher(data);

	    return m.find();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}

	return false;
    }

}