import java.io.*;
import parse.*;
import def.*;

public class ParsingRules {

    static public void main(String argv[]) {
       	try {

	    parser p = new parser(new lexer(new FileReader(argv[0])));
	    Object d = p.parse().value;

	    if (d instanceof ThreatDefinition) {
		((ThreatDefinition) d).print();
		System.out.print("\n\n");
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
}