package ids;

import java.util.*;
import java.io.*;
import parse.*;

public class IDS
{
    static final String usage = "Usage: IDS [rule_file] [pcap_file]";
    /**
     * @param args
     */
    public static void main(String[] argv) {

	if (argv.length < 2) {
	    System.err.println(usage);
	    System.exit(1);
	}

	System.out.println("Hello!");
	System.exit(0);

	try {
	    parser p = new parser(new lexer(new FileReader(argv[0])));
	    Object d = p.parse().value;    
	} catch (Exception e) {
	}
    }

}
