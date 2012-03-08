package ids;

import java.util.List;

public class ProtocolRule implements AbstractRule {

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

}
