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

    /**
     * Returns an iterator for the packets put into this TCPStream. 
     * The iterator respects the defined order of the TCP packet protocol
     */
    public Iterator<TCPPacket> iterator() {
	//Inline sort.
	Collections.sort(packetList, new TCPPacketComparator());
	return packetList.iterator();
    }

    public void add(TCPPacket packet) {
	packetList.add(packet);
    }
	
    /**
     * Private internal class to define a Comparator for TCPPackets
     */
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
