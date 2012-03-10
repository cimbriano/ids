package stream;

import java.util.Iterator;
import net.sourceforge.jpcap.net.IPPacket;

public class TCPStream implements AbstractStream {

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
		return new TCPStreamIterator(this);
	}

	@Override
	public void add(IPPacket packet) {
		// TODO Auto-generated method stub
		
	}
	
	private class TCPStreamIterator implements Iterator<IPPacket> {
	
		public TCPStreamIterator(TCPStream tcpStream){
			//TODO this should take a parameter
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
