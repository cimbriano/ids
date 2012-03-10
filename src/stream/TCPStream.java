package stream;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import net.sourceforge.jpcap.net.IPPacket;
import net.sourceforge.jpcap.net.TCPPacket;


public class TCPStream implements AbstractStream {
	ArrayList<IPPacket> packetList;
	StreamKey streamKey;
	
	public TCPStream(StreamKey key){
		packetList = new ArrayList<IPPacket>();
		streamKey = key;		
	}

	@Override
	public StreamKey getKey() {
		return streamKey;
	}

	@Override
	public boolean isTCP() {
		return true;
	}

	@Override
	public boolean isUDP() {
		return false;
	}

	@Override
	public Iterator<IPPacket> iterator() {
	//Inline sort.
		Collections.sort(packetList, new IPPacketComparator());
		return packetList.iterator();
	}
	

	@Override
	public void add(IPPacket packet) {
		if(packet instanceof TCPPacket) {
			packetList.add((TCPPacket) packet);
		}
	}

	
	private class IPPacketComparator implements Comparator<IPPacket> {


		@Override
		public int compare(IPPacket o1, IPPacket o2) {
			// TODO Auto-generated method stub
			TCPPacket packet1 = (TCPPacket) o1;
			TCPPacket packet2 = (TCPPacket) o2;
			
			long diff = packet1.getSequenceNumber() - packet2.getSequenceNumber();
			
			if(diff > 0) return 1;
			else if(diff < 0) return -1;
			else return 0; 
		}
	
	}
	
}
