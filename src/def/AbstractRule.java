package def;

import net.sourceforge.jpcap.net.*;
import stream.*;

public interface AbstractRule
{

   public void scan(IPPacket packet);

    /*
     *
     */

   public void printRule();

}
