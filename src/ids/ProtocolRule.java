package ids;

import java.util.List;

public class ProtocolRule implements AbstractRule {
	boolean isTcp;
	int srcPort;
	int dstPort;
	int ip;
	List<ProtocolSubrule> subrules;
}
