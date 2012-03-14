
import net.sourceforge.jpcap.net.*;
import java.io.*;
import parse.*;
import def.*;

public class ParsingRules {

    static public void main(String argv[]) {
       	try {

	    parser p = new parser(new lexer(new FileReader(argv[0])));
	    Object d = p.parse().value;

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
}
