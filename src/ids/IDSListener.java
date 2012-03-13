package ids;

import java.util.*;
import net.sourceforge.jpcap.capture.*;
import net.sourceforge.jpcap.net.*;
import stream.*;
import ids.*;

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
	    print((IPPacket) packet, count++);
	    scanner.scan( (IPPacket) packet );
	}
    }

    private void print(IPPacket packet, int packetNumber) {
	System.out.print("Packet ("+packetNumber+"): ");
	System.out.println(packet.toColoredString(true));
    }

}
