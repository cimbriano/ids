package ids;

import java.util.List;

public class ProtocolRule extends AbstractRule
{
    final ProtocolSubruleList subrules;
    final boolean isTcp;
    final Integer srcPort;
    final Integer dstPort;
    final String ip;

    public ProtocolRule(boolean tcp, Integer src, Integer dst, String a,
			ProtocolSubruleList rules) {
	isTcp = tcp;
	srcPort = new Integer(src.intValue());
	dstPort = new Integer(dst.intValue());
	ip = new String(a);

	subrules = rules;
    }

    public void setName(String n) {
	name = new String(n);
    }

    public void printRule() {
	System.out.print("name="+name+"\n");
	System.out.print("type=protocol\n");
	System.out.print("proto="+(isTcp ? "tcp" : "udp")+"\n");
	System.out.print("src_port="+srcPort+"\n");
	System.out.print("dst_port="+dstPort+"\n");
	System.out.print("ip="+ip+"\n");

	for (ProtocolSubrule r : subrules) {
	    System.out.print(r.isSend ? "send=" : "recv=");
	    System.out.print(r.regexp);

	    if (!r.flags.isEmpty()) {
		System.out.print(" with flags=");

		for (Character c : r.flags)
		    System.out.print(c);

		System.out.print("\n");
	    } else
		System.out.print("\n");
	}
    }

}
