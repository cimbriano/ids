package ids;

import java.util.Iterator;
import net.sourceforge.jpcap.net.IPPacket;

public class UDPStream implements AbstractStream {

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
		return null;
	}

	@Override
	public void add(IPPacket packet) {
		// TODO Auto-generated method stub
		
	}

}
