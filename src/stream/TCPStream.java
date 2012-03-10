package stream;

import java.util.Iterator;
import java.util.ArrayList;
import net.sourceforge.jpcap.net.IPPacket;

public class TCPStream implements AbstractStream {

	ArrayList<IPPacket> packetList;
	StreamKey streamKey;
	
	public TCPStream(StreamKey key){
	//TODO Might need to define a comparator
		packetList = new ArrayList<IPPacket>();
		streamKey = key;
		
	}

	public StreamKey getKey() {
		return null;
	}

	@Override
	public boolean isTCP() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUDP() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<IPPacket> iterator() {
		// TODO Auto-generated method stub
		return packetList.iterator();
	}

	@Override
	public void add(IPPacket packet) {
		// TODO Auto-generated method stub
		
	}
	
}
