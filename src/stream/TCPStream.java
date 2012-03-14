package stream;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import net.sourceforge.jpcap.net.TCPPacket;

/**
 * TCPStream.java: builds a list of recevied packets for a particular stream.
 * Packets are returned in sorted order (sorted by sequence number) when
 * object is iterated over. Stream reconstruction (interpreting sequence/ack numbers)
 * is handled elsewhere. This object merely stores the packets and returns them in
 * sorted (by sequence number) order.
 */
public class TCPStream implements Iterable<TCPPacket> {
    List<TCPPacket> packetList;
	
    public TCPStream(){
	packetList = new ArrayList<TCPPacket>();
    }

    public Iterator<TCPPacket> iterator() {
	//Inline sort.
	Collections.sort(packetList, new TCPPacketComparator());
	return packetList.iterator();
    }

    public void add(TCPPacket packet) {
	packetList.add(packet);
    }
	
    private class TCPPacketComparator implements Comparator<TCPPacket> {
	
	@Override
	public int compare(TCPPacket p1, TCPPacket p2) {			
	    long diff = p1.getSequenceNumber() - p2.getSequenceNumber();
			
	    if(diff > 0) return 1;
	    else if(diff < 0) return -1;
	    else return 0; 
	}
	
    }
	
}
