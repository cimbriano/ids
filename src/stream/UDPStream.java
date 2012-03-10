package stream;

import java.util.Iterator;
import java.util.LinkedList;
import net.sourceforge.jpcap.net.IPPacket;

public class UDPStream implements AbstractStream {
	LinkedList<IPPacket> packetList;
	StreamKey streamKey;
	
	public UDPStream(StreamKey key){
		packetList = new LinkedList<IPPacket>();
		streamKey = key;
	}
	

  public StreamKey getKey() {
			return streamKey;
  }

	@Override
	public boolean isTCP() {
		return false;
	}

	@Override
	public boolean isUDP() {
		return true;
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
