package def;

import java.util.*;

/*
 * ProtocolSubruleList.java: Wrapper for a list of protocol subrules.
 * java_cup has difficulty with generics so we had to introduce wrapper
 * classes to construct lists of ProtocolSubrules during parsing.
 */
public class ProtocolSubruleList extends ArrayList<ProtocolSubrule> {
 
   public ProtocolSubruleList(ProtocolSubrule r) {
    	super();
    	add(r);
    }   

}