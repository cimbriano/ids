package ids;

import java.util.*;
import java.io.*;
import parse.*;
import def.*;

public class IDS
{
    static final String usage = "Usage: IDS [rule_file] [pcap_file]";

    /**
     * @param args
     */
    public static void main(String[] argv) {
    	if (argv.length < 2) die(usage);	

    	ThreatDefinition d = loadDefinition(argv[0]);
    }

    private static ThreatDefinition loadDefinition(String fname) {
	try {
	    parser p = new parser(new lexer(new FileReader(fname)));
	    Object d = p.parse().value;

	    if (d instanceof ThreatDefinition)
		return (ThreatDefinition) d;
	} catch (FileNotFoundException e) {
	    die("File not found: "+fname);
	} catch (Exception e) {
		die("Parser exception: "+e.getMessage());
	}

	die("Error loading ThreatDefinition: "+fname);

	return null;
    }

    private static void die(String msg) {
    	System.err.println(msg);
    	System.exit(1);
    }

}
