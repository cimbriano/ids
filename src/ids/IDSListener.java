package ids;

import java.util.*;

import net.sourceforge.jpcap.capture.PacketListener;
import net.sourceforge.jpcap.net.Packet;
import stream.*;

public class IDSListener implements PacketListener {

    final HashMap<StreamKey, AbstractStream> streams = new HashMap<StreamKey, AbstractStream>();

    public void packetArrived(Packet packet) {
	// TODO Auto-generated method stub
		
    }

    private class StreamKey {
    }

}
