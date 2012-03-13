package ids;

import java.util.*;
import net.sourceforge.jpcap.capture.*;
import net.sourceforge.jpcap.net.*;
import stream.*;
import ids.*;
import out.*;

public class IDSListener implements PacketListener
{
    final IDSScanner scanner;
    int count;

    public IDSListener(IDSScanner scanner) {
	this.scanner = scanner;
	count = 0;
    }

    public void packetArrived(Packet packet) {
	if (packet instanceof IPPacket) {
	    Alert.printPacket((IPPacket) packet);
	    scanner.scan( (IPPacket) packet );
	}
    }

}
