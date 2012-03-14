package stream;

/**
 * TCPStreamTuple.java: A 4-tuple of (srcIp, srcPort, dstIp, dstPort). Used
 * to uniquely identifiy TCP sessions when there may be multiple sessions
 * in question for intrusion detection. Implements hashCode() so that TCPStream
 * objects can be stored in a HashMap with their 4-tuple as key.
 */
public class TCPStreamTuple {
    final int srcPort;
    final int dstPort;
    final String srcIp;
    final String dstIp;

    public TCPStreamTuple(String srcIp, int srcPort, String dstIp, int dstPort ) {
	this.srcPort = srcPort;
	this.dstPort = dstPort;
	this.srcIp = srcIp;
	this.dstIp = dstIp;
    }

    @Override
    public int hashCode() {
	String s = srcIp + dstIp + srcPort + dstPort;

	return s.hashCode();
    }

    @Override
    public boolean equals(Object o) {
	if (o instanceof TCPStreamTuple)
	    return equals((TCPStreamTuple) o);
	else
	    return false;
    }

    public boolean equals(TCPStreamTuple k) {
	if (srcPort != k.srcPort)
	    return false;

	if (dstPort != k.dstPort)
	    return false;

	if (!srcIp.equals(k.srcIp))
	    return false;

	if (!dstIp.equals(k.dstIp))
	    return false;

	return true;
    }	
	
}
