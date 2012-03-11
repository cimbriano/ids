package def;

import java.util.*;

public class ProtocolSubrule
{
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
