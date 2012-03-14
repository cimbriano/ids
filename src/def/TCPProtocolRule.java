package def;

import net.sourceforge.jpcap.net.*;
import java.util.*;
import stream.*;
import def.*;

/**
 * TCPProtocolRule.java: Corresponds to a tcp protocol rule in the grammar.
 * Contains all the information for checking tcp protocol rules and the 
 * TCPProtocolRuleChecker to do so. Passes packets to the checker for checking.
 */
public class TCPProtocolRule implements AbstractRule {
    final TCPProtocolRuleChecker checker;

    final ProtocolSubruleList subrules;
    final int srcPort;
    final int dstPort;
    final String ip;

    public TCPProtocolRule(int src, int dst, String ip, ProtocolSubruleList r) {
	srcPort = src;
	dstPort = dst;
	this.ip = ip;
	subrules = r;

	checker = new TCPProtocolRuleChecker(subrules.size());
    }

    @Override
    public void scan(IPPacket packet, Rule rule, ThreatDefinition threat) {
	if (packet instanceof TCPPacket) {
	    checker.add( (TCPPacket) packet, this, rule.name, threat.host );
	}
    }

    /*
     *
     */

    public void printRule() {
	System.out.print("type=protocol\n");
	System.out.print("proto=tcp\n");
	System.out.print("src_port="+srcPort+"\n");
	System.out.print("dst_port="+dstPort+"\n");
	System.out.print("ip="+ip+"\n");

	for (ProtocolSubrule r : subrules) {
	    System.out.print((r.isSend ? "send=" : "recv=")+"\""+r.regexp+"\"");

	    if (!r.flags.isEmpty()) {
		System.out.print(" with flags=");

		for (Character c : r.flags)
		    System.out.print(c);
	    }

	    System.out.print("\n");
	}
    }

}
