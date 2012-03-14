package def;

import java.util.*;

/*
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

	if (f == null) {
	    flags = null;
	} else {
	    flags = new ArrayList<Character>();

	    for (int x = 0; x < f.length(); x++)
		flags.add(new Character(f.charAt(x)));
	}
    }

}
