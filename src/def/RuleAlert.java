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
	    //System.out.format("(%08d): ", packet.getId());
	    //System.out.println(packet.toColoredString(true));

	    printPacket(packet);
	}
    
	System.out.println(footer);
  	
    }

    public static void printPacket(IPPacket packet) {

	try {
	    String data = new String(packet.getData(), "ISO-8859-1");

	    System.out.format("(%08d): ", packet.getId());
	    System.out.print(packet.toColoredString(true));

	    for (int i = 0; i < data.length() && i < 12; i++) {
		char c = data.charAt(i);

		if (c > 32 && c < 127)
		    System.out.print(c);
		else
		    System.out.print("_");

	    }

	    System.out.println();
	} catch (Exception e) {

	    e.printStackTrace();

	}
	    

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