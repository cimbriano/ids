package stream;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import net.sourceforge.jpcap.net.IPPacket;

public class TCPStream implements AbstractStream {
	ArrayList<IPPacket> packetList;
	StreamKey streamKey;
	
	public TCPStream(StreamKey key){
		packetList = new ArrayList<IPPacket>();
		streamKey = key;		
	}

	@Override
	public StreamKey getKey() {
		return null;
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
		// TODO Auto-generated method stub
		
	}

	
	private class IPPacketComparator implements Comparator<IPPacket> {
	
		@Override
		public int compare(IPPacket o1, IPPacket o2) {
			// TODO Auto-generated method stub
			return 0;
		}
	
	}
	
}
