package out;

import net.sourceforge.jpcap.net.*;
import java.util.*;
import java.io.*;

/* Alert.java: A final class with static methods to handle output. Provides two
 * functions: alert() and printPacket(). alert() will print an alert and packet
 * sequence to stdout. printPacket() will print a packet's ID, signature and first
 * 4 bytes of data to stdout.
 */
public final class Alert {
    private static final int WIDTH = 80; //terminal width

    public static void alert(String ruleName, List<? extends IPPacket> matchedPackets) {
	String h1 = buildHeader("Alert! Matched rule: " + ruleName);
	String h2 = buildHeader("Dumping sequence of matched packets");
	String footer = buildHeader("Continuing reading packet stream");				

	System.out.println(h1);
	System.out.println(h2);
    
	for(IPPacket packet : matchedPackets) {
	    printPacket(packet);
	}
    
	System.out.println(footer);
    }

    public static void printPacket(IPPacket packet) {
	System.out.format("(%08d): ", packet.getId());
	System.out.print(packet.toColoredString(true));

	printBytes(packet, 4);

	System.out.println();
    }

    private static void printBytes(IPPacket packet, int numBytes) {
	try {
	    String data = new String(packet.getData(), "ISO-8859-1");
	    boolean first = true;

	    if (data.length() == 0) return;

	    System.out.print(" [");

	    for (int i = 0; i < numBytes && i < data.length(); i++) {
		char c = data.charAt(i);

		if (!first)
		    System.out.print(" ");

		if (c > 31 && c < 127)
		    System.out.format("%4c", c);
		else
		    System.out.format("0x%02x", (int)c);

		first = false;
	    }

	    System.out.print("]");

	}  catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}
    }

    private static String buildHeader(String text) {
	StringBuilder header = new StringBuilder();
	String s = " " + text + " ";

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
