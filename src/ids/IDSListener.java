package ids;

import net.sourceforge.jpcap.capture.*;
import net.sourceforge.jpcap.net.*;
//import stream.*;
import out.*;

/*
 * IDSListener.java: Listens for packets and passes them to IDSScanner
 * for scanning. Also, prints the packet to stdout.
 */
public class IDSListener implements PacketListener {
    final IDSScanner scanner;
    private int count;

    public IDSListener(IDSScanner scanner) {
	this.scanner = scanner;
	count = 0;
    }

    public void packetArrived(Packet packet) {
	if (packet instanceof IPPacket) { //ignore non-ip packets
	    Alert.printPacket( (IPPacket) packet );
	    scanner.scan( (IPPacket) packet );
	}
    }

}
