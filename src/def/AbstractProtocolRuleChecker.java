package def;

import net.sourceforge.jpcap.net.*;
import java.util.regex.*;
import java.io.*;

/** AbstractProtocolRuleChecker.java: Base class for all protocol checkers (tcp and udp).
 * Implements function for scanning a packet data and enforces an interface for
 * adding packets to the checker.
 */
public abstract class AbstractProtocolRuleChecker<T extends IPPacket, S extends AbstractRule> {

    public abstract void add(T packet, S rule, String rulename, String host);

    /**
     * Checks the contents specfied in the subRule against the contents of the packet body.
     * @param packet
     * @param subRule
     * @return true if the regex specified in <subRule> is found anywhere within the packets contents
     */
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