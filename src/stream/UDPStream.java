package stream;

import java.util.Iterator;
import net.sourceforge.jpcap.net.IPPacket;

public class UDPStream implements AbstractStream {

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
		return true;
	}

	@Override
	public Iterator<IPPacket> iterator() {
		// TODO Auto-generated method stub
		return new UDPStreamIterator(this);
	}

	@Override
	public void add(IPPacket packet) {
		// TODO Auto-generated method stub
		
	}
	
	private class UDPStreamIterator implements Iterator<IPPacket>{


		public UDPStreamIterator(UDPStream udpStream) {
			//TODO implement this 
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public IPPacket next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
