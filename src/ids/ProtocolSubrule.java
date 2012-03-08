package ids;

import java.util.*;

public class ProtocolSubrule {

    final ArrayList<Character> flags = new ArrayList<Character>();
    final boolean isSend;
    final String regexp;

    public ProtocolSubrule(boolean s, String r, String f) {
	regexp = new String(r);
	isSend = s;

	for (int x = 0; x < f.length(); x++)
	    flags.add(new Character(f.charAt(x)));
    }

}
