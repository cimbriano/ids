package ids;

import net.sourceforge.jpcap.net.*;
import stream.*;
import def.*;

public class IDSScanner
{
    final ThreatDefinition threat;
	
    public IDSScanner(ThreatDefinition threat){
	this.threat = threat;
    }

    public void scan(IPPacket packet) {
	for (Rule r : threat)
	    r.scan(packet, threat);
    }

}
