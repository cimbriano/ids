package stream;

import java.util.Iterator;
import net.sourceforge.jpcap.net.IPPacket;

public class TCPStream implements AbstractStream {

    public boolean isTCP() {
	// TODO Auto-generated method stub
	return false;
    }


    public boolean isUDP() {
	// TODO Auto-generated method stub
	return false;
    }


    public Iterator<IPPacket> iterator() {
	// TODO Auto-generated method stub
	return null;
    }


    public void add(IPPacket packet) {
	// TODO Auto-generated method stub
	
    }

    public StreamKey getKey() {
	return null;
    }
    	
}
