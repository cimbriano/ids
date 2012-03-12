package def;

import java.util.*;
import net.sourceforge.jpcap.net.*;
import stream.*;

public class TCPRule implements AbstractRule
{
    final HashMap<TCPStreamTuple, TCPStream> streams;

    final Integer srcPort;
    final Integer dstPort;
    final String  ip;
    final boolean isSend;
    final String  regexp;

    public TCPRule(Integer src, Integer dst, String ip, boolean s, String r) {
	 this.ip = ip;
	 srcPort = src;
	 dstPort = dst;
	 isSend  = s;
	 regexp  = r;

	 streams = new HashMap<TCPStreamTuple, TCPStream>();
     }

     @Override
     public void scan(IPPacket packet, Rule rule, ThreatDefinition threat) {
	 if (packet instanceof TCPPacket) {

	 }
     }

     /*
      *
      */

    public void printRule() {
	System.out.print("type=tcp_stream\n");
	System.out.print("src_port="+srcPort+"\n");
	System.out.print("dst_port="+dstPort+"\n");
	System.out.print("ip="+ip+"\n");
	System.out.print((isSend ? "send=" : "recv=")+"\""+regexp+"\"\n");
    }

}
