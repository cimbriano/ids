package def;

import net.sourceforge.jpcap.net.*;
import java.util.*;
import stream.*;
import def.*;

public class ProtocolRule implements AbstractRule
{
    final ProtocolSubruleList subrules;
    final boolean isTCP;
    final int srcPort;
    final int dstPort;
    final String ip;

    public ProtocolRule(boolean tcp, int src, int dst, String ip, ProtocolSubruleList r) {
	srcPort = src;
	dstPort = dst;
	this.ip = ip;
	subrules = r;
	isTCP = tcp;
    }

    @Override
    public void scan(AbstractStream stream) {
    }

    /*
     *
     */

    public void printRule() {
	System.out.print("type=protocol\n");
	System.out.print("proto="+(isTCP ? "tcp" : "udp")+"\n");
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
