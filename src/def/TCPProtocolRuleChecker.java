package def;

import net.sourceforge.jpcap.net.TCPPacket;


public class TCPProtocolRuleChecker implements AbstractProtocolRuleChecker<TCPPacket, TCPProtocolRule> {

    List<List<TCPPacket>> store;

    public TCPProtocolRulerChecer() {
    }

    @Override
    public void add(TCPPacket packet, TCPProtocolRule rule, String ruleName, String host) {
		// TODO Auto-generated method stub
		
    }

}