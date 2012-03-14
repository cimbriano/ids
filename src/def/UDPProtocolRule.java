package def;

import net.sourceforge.jpcap.net.*;
import java.util.*;
import stream.*;
import def.*;

/**
 * UDPProtocolRule.java: Corresponds to a udp protocol rule in the grammar.
 * Contains all the information for checking udp protocol rules and the 
 * UDPProtocolRuleChecker to do so. Passes packets to the checker for checking.
 * Also, contains the list of subrules.
 */
public class UDPProtocolRule implements AbstractRule {
    final UDPProtocolRuleChecker checker;

    final ProtocolSubruleList subrules;
    final int srcPort;
    final int dstPort;
    final String ip;

    public UDPProtocolRule(int src, int dst, String ip, ProtocolSubruleList r) {
	srcPort = src;
	dstPort = dst;
	this.ip = ip;
	subrules = r;

	checker = new UDPProtocolRuleChecker(subrules.size());
    }

    @Override
    public void scan(IPPacket packet, Rule rule, ThreatDefinition threat) {
	if (packet instanceof UDPPacket) {
	    checker.add( (UDPPacket) packet, this, rule.name, threat.host);
	}
    }

    /**
     *
     */

    public void printRule() {
	System.out.print("type=protocol\n");
	System.out.print("proto=udp\n");
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
