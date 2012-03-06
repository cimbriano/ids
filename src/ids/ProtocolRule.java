package ids;

import java.util.List;

public class ProtocolRule extends Rule {
	boolean isTcp;
	int srcPort;
	int dstPort;
	int ip;
	List<ProtocolSubrule> subrules;
}
