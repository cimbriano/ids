import java.io.*;
import ids.*;

public class ParsingRules {

    static public void main(String argv[]) {
       	try {
	    System.out.print("Parsing (" + argv[0] + ")... ");

	    parser p = new parser(new lexer(new FileReader(argv[0])));
	    Object d = p.parse().value;

	    if (d instanceof ThreatDefinition) {
		System.out.println("done.");
		((ThreatDefinition) d).print();
	    } else
		System.out.println("failed.");
	    
	    /*lexer l = new lexer(new FileReader(argv[0]));
	    java_cup.runtime.Symbol s = l.next_token();

	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    s = l.next_token();
	    System.out.println(s.toString());
	    System.out.println(sym.SET_HOST);*/

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
}