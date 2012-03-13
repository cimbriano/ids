package def;

import net.sourceforge.jpcap.net.*;
import java.util.*;

final class RuleAlert 
{
    private static final int WIDTH = 80;

    public static void alert(String ruleName, List<? extends IPPacket> matchedPackets) {
	String header = buildHeader(ruleName);
	String footer = buildFooter();

	System.out.println(header);
    
	for(IPPacket packet : matchedPackets){
	    System.out.format("(%08d): ", packet.getId());
	    System.out.println(packet.toColoredString(true));
	}
    
	System.out.println(footer);
  	
    }

    private static String buildHeader(String ruleName){
	String s = " Alert! Matched rule: " + ruleName + " ";
	StringBuilder header = new StringBuilder();

	for (int i = 0; i < (WIDTH-s.length())/2; i++)
	    header.append("*");

	header.append(s);

	while (header.length() < 80)
	    header.append("*");

	return new String(header);
    }

    private static String buildFooter() {
	StringBuilder footer = new StringBuilder();
	
	for (int i = 0; i < WIDTH; i++)
	    footer.append("*");

	return new String(footer);
    }

}