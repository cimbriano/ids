package def;

import net.sourceforge.jpcap.net.UDPPacket;
import java.util.*;

public class UDPProtocolRuleChecker implements AbstractProtocolRuleChecker<UDPPacket>
{
    final List<UDPPacket[]> store;
    final int ssize;

    public UDPProtocolRuleChecker(int subrules) {
	store = new ArrayList<UDPPacket[]>();
	ssize = subrules;
    }

    @Override
    public void add(UDPPacket packet) {
	for (UDPPacket[] partialMatch : store) {
	    
	    


	}		
    }

}