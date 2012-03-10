import net.sourceforge.jpcap.capture.*;
import net.sourceforge.jpcap.net.*;

public class ReadingFromPcap {

    static public void main(String argv[]) {
	
	PacketCapture pc = new PacketCapture();

	try {
	    pc.addPacketListener(new HandlePacket());
	    pc.openOffline(argv[0]);
	    pc.capture(-1);
	} catch (Exception e) {
	}
	
    }

    static public class HandlePacket implements PacketListener {

	public void packetArrived(Packet p) {
	    if (p instanceof TCPPacket) {
		String s = ((TCPPacket)p).toColoredVerboseString(true);

		System.out.println(s);

	    } else if (p instanceof UDPPacket) {
		String s = ((UDPPacket)p).toColoredString(true);

		System.out.println(s);
	    }
	}

    }

}
