package stream;

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
	
	/*
public boolean match(boolean tcp, int srcPort, int destPort, String ip){
		if(		this.isTCP == tcp && 
				this.srcPort == srcPort &&
				this.dstPort == destPort &&
				this.srcIp.equals(ip)) {
			return true;
		} else {
			return false;
		}
		
	}
*/

}
