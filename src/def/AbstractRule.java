package def;

import net.sourceforge.jpcap.net.*;
import stream.*;

public interface AbstractRule
{

    public void scan(IPPacket packet, Rule rule, ThreatDefinition threat);

    /*
     *
     */

   public void printRule();

}
