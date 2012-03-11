package def;

import net.sourceforge.jpcap.net.UDPPacket;
import java.util.regex.*;
import java.util.*;
import def.*;

public class UDPProtocolRuleChecker implements AbstractProtocolRuleChecker<UDPPacket, UDPProtocolRule> {

    final List<List<UDPPacket>> store;
    final int ssize;

    public UDPProtocolRuleChecker(int subrules) {
	store = new ArrayList<List<UDPPacket>>();
	ssize = subrules;
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
		System.out.println("ZOMG!!! AlErT!!!!");
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

    private boolean checkContents(UDPPacket packet, ProtocolSubrule subRule) {
	String data = new String(packet.getUDPData());

	Pattern p = Pattern.compile(subRule.regexp);
	Matcher m = p.matcher(data);

	return m.find();
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