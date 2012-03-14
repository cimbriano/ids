package def;

import net.sourceforge.jpcap.net.*;
import stream.*;

/**
 * AbstractRule.java: Enforces an interface on all rules (protocol and stream)
 * to scan packets and print itself.
 */
public interface AbstractRule {

    public void scan(IPPacket packet, Rule rule, ThreatDefinition threat);

    /*
     *
     */

   public void printRule();

}
