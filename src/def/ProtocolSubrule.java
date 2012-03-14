package def;

import java.util.*;

/**
 * ProtocolSubrule.java: Object to hold the contents of a protocol subrule.
 * Used to construct the threat definition during parsing and used to scan
 * packets during packet capturing.
 */
public class ProtocolSubrule {
    final List<Character> flags;
    final boolean isSend;
    final String regexp;

    public ProtocolSubrule(boolean s, String r, String f) {
	regexp = r;
	isSend = s;

	
	/*flags is null if no flag conditions were specfied
	 * if flag conditions were specfied, but the specification was that all 
	 * flags were off, then flags is an empty, but initialized List
	 * 
	 */
	
	if (f == null) {
	    flags = null;
	} else {
	    flags = new ArrayList<Character>();

	    for (int x = 0; x < f.length(); x++)
		flags.add(new Character(f.charAt(x)));
	}
    }

}
