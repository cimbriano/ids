package ids;

import java.util.*;

import net.sourceforge.jpcap.capture.*;
import net.sourceforge.jpcap.net.*;
import stream.*;
import ids.*;

public class IDSListener implements PacketListener {

    //final HashMap<StreamKey, AbstractStream> streams =
    // new HashMap<StreamKey, AbstractStream>();
    
    final IDSScanner scanner;

    public IDSListener(IDSScanner scanner) {
	this.scanner = scanner;
    }

    public void packetArrived(Packet packet) {
	TCPPacket p = (TCPPacket) packet;

	System.out.print(p.toColoredVerboseString(true));
	System.out.println(": "+p.getPayloadDataLength());
    }

    /*
    private AbstractStream packetArrived(UDPPacket p) {
	StreamKey k = new StreamKey(false, p.getSourcePort(), p.getDestinationPort(),
				    p.getSourceAddress(), p.getDestinationAddress());
	AbstractStream stream = getStream(k, false);
	
	stream.add(p);

	return stream;
    }

    private AbstractStream packetArrived(TCPPacket p) {
	StreamKey k = new StreamKey(true, p.getSourcePort(), p.getDestinationPort(),
				    p.getSourceAddress(), p.getDestinationAddress());
	AbstractStream stream = getStream(k, true);
	
	stream.add(p);
	
	return stream;
    }

    private AbstractStream getStream(StreamKey k, boolean isTCP) {
	if (streams.containsKey(k))
	    return streams.get(k);

	AbstractStream stream = isTCP ? new TCPStream(k) : new UDPStream(k);
	streams.put(k, stream);

	return stream;
	}*/

}
