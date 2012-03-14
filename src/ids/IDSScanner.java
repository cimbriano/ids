package ids;

import net.sourceforge.jpcap.net.*;
import stream.*;
import def.*;

/**
 * IDSScanner.java: Takes a packet to scan and runs through
 * all of the threatdefinitions rules (all the rules defined in
 * the rule file) as there may be many.
 */
public class IDSScanner {
    final ThreatDefinition threat;
	
    public IDSScanner(ThreatDefinition threat){
	this.threat = threat;
    }

    public void scan(IPPacket packet) {
	for (Rule r : threat)
	    r.scan(packet, threat);
    }

}
