package def;

import java.util.*;
import net.sourceforge.jpcap.net.*;
import java.util.regex.*;
import java.io.*;
import stream.*;
import java.lang.*;

public class TCPStreamRule implements AbstractRule
{
    final HashMap<TCPStreamTuple, TCPStream> streams;
    final HashMap<List<TCPPacket>, Boolean> alertedPackets;

    final Integer srcPort;
    final Integer dstPort;
    final String  ip;
    final boolean isSend;
    final String  regexp;

    public TCPStreamRule(Integer src, Integer dst, String ip, boolean s, String r) {
	 this.ip = ip;
	 srcPort = src;
	 dstPort = dst;
	 isSend  = s;
	 regexp  = r;

	 streams = new HashMap<TCPStreamTuple, TCPStream>();
	 alertedPackets = new HashMap<List<TCPPacket>, Boolean>();
     }

     @Override
     public void scan(IPPacket packet, Rule rule, ThreatDefinition threat) {
	 if (packet instanceof TCPPacket) {
	     if (matchRuleStream((TCPPacket) packet, threat.host))
		 scan((TCPPacket) packet, rule, threat);
	 }
     }

    private void scan(TCPPacket packet, Rule rule, ThreatDefinition threat) {
	TCPStream stream = addPacketToStreams(packet);

	scanStream(stream);
    }

    //Rule rule;

    private void scanStream(TCPStream stream) {
	List<TCPPacket> toScan = new ArrayList<TCPPacket>();
	TCPPacket prevPacket = null;

	for (TCPPacket packet : stream) {

	    if (prevPacket == null || validNeighbors(prevPacket, packet)) {
		toScan.add(packet);
	    } else {
		scanPartialStream(toScan);
		toScan.clear();
		toScan.add(packet);
	    } 

	    prevPacket = packet;

	}

	if (toScan.size() > 0)
	    scanPartialStream(toScan);
   
    }

    private boolean scanPartialStream(List<TCPPacket> partialStream) {
	StringBuilder sb = new StringBuilder();
	
	try {
	    for (TCPPacket p : partialStream) {
		sb.append( new String(p.getData(), "ISO-8859-1") );
	    }
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}

	Matcher m = buildMatcher(sb);

	while (m.find()) 
	    extractAndAlertPackets(partialStream, m.start(), m.end());

	return false;
    }

    private Matcher buildMatcher(StringBuilder contents) {
	String data = new String(contents);

	Pattern p = Pattern.compile(regexp);
	Matcher m = p.matcher(data);

	return m;
    }

    private void extractAndAlertPackets(List<TCPPacket> partialStream, int start, int end) {
	List<TCPPacket> alertList = new ArrayList<TCPPacket>();
	int packetStart = 0;
	int packetEnd;

	for (TCPPacket p : partialStream) {
	 
	    packetEnd = packetStart + p.getPayloadDataLength();

	    if (packetStart < start) {
		if (start < packetEnd) {
		    alertList.add(p);
		}
	    } else if (start <= packetStart && packetStart < end) {
		alertList.add(p);	    
	    } else if (end <= packetStart) {
		break;
	    }

	    packetStart = packetEnd;

	}

	alert("", alertList);
    }

    private void alert(String rulename, List<TCPPacket> matchedPackets) {
	if (notAlerted(matchedPackets)) {
	    //System.out.println("\nAlert!! Matched rule '" + rulename +"'");

	    //for (TCPPacket p : matchedPackets) {
	    //	System.out.println(p.toColoredString(true));
	    //}

	    //System.out.println("*********************\n");
	    RuleAlert.alert(rulename, matchedPackets);
	}
    }

    private boolean notAlerted(List<TCPPacket> packets) {
	Boolean b = alertedPackets.get(packets);

	if (b == null)
	    alertedPackets.put(packets, new Boolean(true));
	else
	    return false;

	return true;
    }

    private boolean validNeighbors(TCPPacket p1, TCPPacket p2) {
	if ((p1.getSequenceNumber()+p1.getPayloadDataLength()) == p2.getSequenceNumber() &&
	    p1.getAcknowledgementNumber() == p2.getAcknowledgementNumber())
	    return true;

	return false;
    }

    private TCPStream addPacketToStreams(TCPPacket packet) {
	TCPStreamTuple tuple = new TCPStreamTuple(packet.getSourceAddress(), packet.getSourcePort(),
						  packet.getDestinationAddress(), packet.getDestinationPort());
	TCPStream stream;

	if (streams.containsKey(tuple)) {
	    stream = streams.get(tuple);
	} else {
	    stream = new TCPStream();
	    streams.put(tuple, stream);
	}

	stream.add(packet);

	return stream;
    }

    private boolean matchRuleStream(TCPPacket p, String host) {
	if (isSend) {
	    if ((p.getSourceAddress().equals(host)) &&
		(p.getSourcePort() == srcPort || srcPort == 0) &&
		(p.getDestinationAddress().equals(ip) || ip.equals("*.*.*.*")) &&
		(p.getDestinationPort() == dstPort || dstPort == 0))

		return true;
	} else {

	    if ((p.getSourceAddress().equals(ip) || ip.equals("*.*.*.*")) &&
		(p.getSourcePort() == dstPort || dstPort == 0) &&
		(p.getDestinationAddress().equals(host)) &&
		(p.getDestinationPort() == srcPort || srcPort == 0))

		return true;
	}	

	return false;
    }

     /*
      *
      */

    public void printRule() {
	System.out.print("type=tcp_stream\n");
	System.out.print("src_port="+srcPort+"\n");
	System.out.print("dst_port="+dstPort+"\n");
	System.out.print("ip="+ip+"\n");
	System.out.print((isSend ? "send=" : "recv=")+"\""+regexp+"\"\n");
    }

}
