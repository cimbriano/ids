package def;

import net.sourceforge.jpcap.net.*;
import java.util.*;
import stream.*;
import def.*;

public class UDPProtocolRule implements AbstractRule
{
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
    public void scan(IPPacket packet) {
	if (packet instanceof UDPPacket) {
	    checker.add( (UDPPacket) packet );
	}
   }

    /*
     *
     */

    public void printRule() {
	System.out.print("type=protocol\n");
	System.out.print("proto=udp\n");
	System.out.print("src_port="+srcPort+"\n");
	System.out.print("dst_port="+dstPort+"\n");
	System.out.print("ip="+ip+"\n");

	for (ProtocolSubrule r : subrules) {
	    System.out.print((r.isSend ? "send=" : "recv=")+r.regexp);

	    if (!r.flags.isEmpty()) {
		System.out.print(" with flags=");

		for (Character c : r.flags)
		    System.out.print(c);
	    }

	    System.out.print("\n");
	}
    }

}
