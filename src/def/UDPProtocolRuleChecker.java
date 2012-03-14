package def;

import net.sourceforge.jpcap.net.*;
import java.util.*;
import out.*;

/**
 * UDPProtocolRuleChecker.java: Does all the heavy lifting for checking 
 * udp protocol rules. It provides an interface to add packets to the checker.
 * The checker keeps an array of arrays representing a partially matched protocol subrule sequence. 
 * 
 * Suppose the index i represents a particular subrule sequence that is partially matched by some number of packets
 * already scanned. Then the index j is the particular subrule in the sequence.
 * 
 * When a packet arrives, it is scanned against the 'next' sub-rule in all i partial sequences,
 * if it matches the next subrule to be matched, the packet is added to that partial sequence. If it doesn't match, the entire
 * subsequence is discarded since the udp sub rule must be matched in order.
 * 
 * If a row is filled, the entire udp subrule sequence has been matched by some set of packets, this will trigger an alert to
 * standard out showing some details about the packets causing the alert, 
 * the name of the matched rule and a few bytes from the packet contents 
 */
public class UDPProtocolRuleChecker extends AbstractProtocolRuleChecker<UDPPacket, UDPProtocolRule> {

    final List<List<UDPPacket>> store;
    final int ssize;

    public UDPProtocolRuleChecker(int numSubRules) {
	store = new ArrayList<List<UDPPacket>>();
	ssize = numSubRules;
    }

    @Override
    public void add(UDPPacket candidate, UDPProtocolRule rule, String rulename, String host) {
	List<List<UDPPacket>> toRemove = new ArrayList<List<UDPPacket>>(); 

	for (List<UDPPacket> partialMatch : store) {
	    UDPPacket first = getFirst(partialMatch);

	    if (first != null) {
		if (matchStream(first, candidate)) {
		    if (matchNextSubRule(partialMatch, candidate, rule, host)) {
			partialMatch.add(candidate);
		    } else {
			toRemove.add(partialMatch);
		    }
		} //checkedStream failed
	    } //something really wrong!
	}

	for (List<UDPPacket> list : toRemove)//cleanup store
	    store.remove(list);

	if (matchSubRule(candidate, rule, 0, host)) {
	    List<UDPPacket> l = new ArrayList<UDPPacket>();
	    l.add(candidate);

	    store.add(l);
	} //new partialMatch

	toRemove =  new ArrayList<List<UDPPacket>>(); 

	/* TODO */
	for (List<UDPPacket> list : store)
	    if (list.size() == ssize) {
	    
		Alert.alert(rulename, list);
		
		//System.out.println("ZOMG!!! AlErT!!!!");
		toRemove.add(list);
	    }

	for (List<UDPPacket> list : toRemove)//cleanup store
	    store.remove(list);

    }

    private UDPPacket getFirst(List<UDPPacket> list) {
	if (list.isEmpty())
	    return null;

	return list.get(0);
    }

    private boolean matchNextSubRule(List<UDPPacket> partialMatch, UDPPacket candidate, UDPProtocolRule rule, String host) {
	int nextIndex = partialMatch.size();
	return matchSubRule(candidate, rule, nextIndex, host);
    }

    private boolean matchSubRule(UDPPacket candidate, UDPProtocolRule rule, int index, String host) {
	ProtocolSubrule subRule;

	try {
	    subRule = rule.subrules.get(index);
	    
	    if ((subRule.isSend && checkSend(candidate, rule, host)) || (!subRule.isSend && checkRecv(candidate, rule, host)))
		if (checkContents(candidate, subRule))
		    return true;		

	} catch (IndexOutOfBoundsException e) {
	    e.printStackTrace();
	}

	return false;
    }

    private boolean checkSend(UDPPacket candidate, UDPProtocolRule rule, String host) {
	if (candidate.getSourceAddress().equals(host) &&
	    (candidate.getSourcePort() == rule.srcPort || rule.srcPort == 0) &&
	    (candidate.getDestinationAddress().equals(rule.ip) || rule.ip.equals("*.*.*.*")) &&
	    (candidate.getDestinationPort() == rule.dstPort || rule.dstPort == 0))
	    return true;
	    
	return false;
    }

    private boolean checkRecv(UDPPacket candidate, UDPProtocolRule rule, String host) {
	if ((candidate.getSourceAddress().equals(rule.ip) || rule.ip.equals("*.*.*.*")) &&
	    (candidate.getSourcePort() == rule.dstPort || rule.dstPort == 0) &&
	    candidate.getDestinationAddress().equals(host) &&
	    (candidate.getDestinationPort() == rule.srcPort || rule.srcPort == 0))
	    return true;

	return false;
    }

    private boolean matchStream(UDPPacket p1, UDPPacket p2) {
	if (p1.getSourceAddress().equals(p2.getSourceAddress()) && p1.getDestinationAddress().equals(p2.getDestinationAddress()))
	    if (p1.getSourcePort() == p2.getSourcePort() && p1.getDestinationPort() == p2.getDestinationPort())
		return true;

	if (p1.getSourceAddress().equals(p2.getDestinationAddress()) && p1.getDestinationAddress().equals(p2.getSourceAddress()))
	    if (p1.getSourcePort() == p2.getDestinationPort() && p1.getDestinationPort() == p2.getSourcePort())
		return true;

	return false;	
    }
    
}