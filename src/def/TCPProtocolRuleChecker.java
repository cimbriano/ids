package def;

import net.sourceforge.jpcap.net.TCPPacket;
import java.util.regex.*;
import java.util.*;


public class TCPProtocolRuleChecker extends AbstractProtocolRuleChecker<TCPPacket, TCPProtocolRule> {

	final List<TCPPacket[]> store;
	final int ssize;

	public TCPProtocolRuleChecker(int numSubRules) {
		store = new ArrayList<TCPPacket[]>();
		ssize = numSubRules;
	}

	@Override
	public void add(TCPPacket candidate, TCPProtocolRule rule, String ruleName, String host) {
		List<Integer> indices = matchingSubRuleIndices(candidate, rule, host);
		List<TCPPacket[]> toRemove = new ArrayList<TCPPacket[]>();

    //Add this packet to as many new rows as it matches subrules
		for (Integer i : indices) {
			TCPPacket[] row = newRow();

			row[i] = candidate;
			store.add(row);
		}
		
		
		consolidatePotentialMatchList(rule.subrules);

    //Check for full rows which indicate fully matched protocol rule
		for (TCPPacket[] list : store)
			if (isFull(list)) {
				System.out.println("ZOMG!!!!!");
				toRemove.add(list);
			}

    //Remove full rows
		for (TCPPacket[] list : toRemove)
			store.remove(list);
			
		//
		
		
	}

	private boolean isFull(TCPPacket[] list) {
		for (int x = 0; x < ssize; x++)
			if (list[x] == null)
				return false;

		return true;
	}

	private TCPPacket[] newRow() {
		TCPPacket[] list = new TCPPacket[ssize];

		for (int x = 0; x < ssize; x++)
		    list[x] = null;

		return list;
	}

	private List<Integer> matchingSubRuleIndices(TCPPacket candidate, TCPProtocolRule rule, String host) {
		List<Integer> indices = new ArrayList<Integer>();

		for (ProtocolSubrule subRule : rule.subrules)
			if (matchSubRule(candidate, subRule, rule, host))
				indices.add( rule.subrules.indexOf(subRule) );	   

		return indices;
	}

	private boolean matchSubRule(TCPPacket candidate, ProtocolSubrule subRule, TCPProtocolRule rule, String host) {
		if (checkDirection(candidate, subRule, rule, host))
			if (checkContents(candidate, subRule))
				if (checkFlags(candidate, subRule))
					return true;

		return false;
	}

	private boolean checkFlags(TCPPacket candidate, ProtocolSubrule subRule) {
		List<Character> flags = subRule.flags;

		if (flags == null) return true;

		if (candidate.isFin() && !flags.contains('F')) return false;
		if (candidate.isUrg() && !flags.contains('U')) return false;
		if (candidate.isSyn() && !flags.contains('S')) return false;
		if (candidate.isPsh() && !flags.contains('P')) return false;
		if (candidate.isAck() && !flags.contains('A')) return false;
		if (candidate.isRst() && !flags.contains('R')) return false;

		return true;
	}

	private boolean checkDirection(TCPPacket candidate, ProtocolSubrule subRule, TCPProtocolRule rule, String host) {
		if ((subRule.isSend && checkSend(candidate, rule, host)) || (!subRule.isSend && checkRecv(candidate, rule, host)))
			return true;

		return false;
	}

	private boolean checkSend(TCPPacket candidate, TCPProtocolRule rule, String host) {
		if (candidate.getSourceAddress().equals(host) &&
				(candidate.getSourcePort() == rule.srcPort || rule.srcPort == 0) &&
				(candidate.getDestinationAddress().equals(rule.ip) || rule.ip.equals("*.*.*.*")) &&
				(candidate.getDestinationPort() == rule.dstPort || rule.dstPort == 0))
			return true;

		return false;
	}

	private boolean checkRecv(TCPPacket candidate, TCPProtocolRule rule, String host) {
		if ((candidate.getSourceAddress().equals(rule.ip) || rule.ip.equals("*.*.*.*")) &&
				(candidate.getSourcePort() == rule.dstPort || rule.dstPort == 0) &&
				candidate.getDestinationAddress().equals(host) &&
				(candidate.getDestinationPort() == rule.srcPort || rule.srcPort == 0))
			return true;

		return false;
	}
	
	
	private void consolidatePotentialMatchList(ProtocolSubruleList rules){
		for(int i = 0; i < ssize - 1; i++){
		  int col1 = i;
		  int col2 = i + 1;
		  
		   //Foreach packet in the first column
		  for(TCPPacket[] candidateRow : store){
		    
		    TCPPacket packet1 = candidateRow[col1];
		    if(packet1 != null) {
		    
		    //compare that packet to every packet in the second column

		       for(TCPPacket[] targetRow : store ){
		        TCPPacket packet2 = targetRow[col2];
		        
		        if (packet2 != null) {
		         if (areValidNeighbors(packet1, packet2, rules.get(col1), rules.get(col2))){
		          //Try to merge rows
		            mergeRows(store.indexOf(candidateRow), store.indexOf(targetRow), col1, col2);  
		            consolidatePotentialMatchList(rules);
		          }
		        }
		        		          
		      } // targetRow loop

		    } //packet1 is null   		  
		  } // CandidateRow loops		  
		  
		}//column pair loop
		
		
	}
	
	private boolean areValidNeighbors(TCPPacket p1, TCPPacket p2, ProtocolSubrule r1, ProtocolSubrule r2){
	   
	   //Check the direction of the rules. 
	   if((r1.isSend && r2.isSend) || (!r1.isSend && !r2.isSend)){
	     //Same Direction
	     
	     if( p1.getSourceAddress().equals(p2.getSourceAddress()) &&
    	     p1.getSourcePort() == p2.getSourcePort() &&
    	     p1.getDestinationAddress().equals(p2.getDestinationAddress()) &&
    	     p1.getDestinationPort() == p2.getDestinationPort() ){
	     
	       //TODO Add sequence and Ack number checking
	         
	       return true;
	     }
	     
	     
	     
	   } else {
	     //Oppostie diretions
	     
	     if( p1.getSourceAddress().equals(p2.getDestinationAddress()) &&
    	     p1.getSourcePort() == p2.getDestinationPort() &&
    	     p1.getDestinationAddress().equals(p2.getSourceAddress()) &&
    	     p1.getDestinationPort() == p2.getSourcePort() ){
	     
	     //TODO Match sequence and ack 
	       return true;
	     }	     
	   }
	   return false; 
	}
	
	public void mergeRows(int row1, int row2, int col1, int col2){	
	   TCPPacket[] rowToRemove = store.get(row2);
	   TCPPacket[] mergedRow = store.get(row1);
	   
	   
	   for(int i = col2; i < rowToRemove.length; i++){
  	   if(mergedRow[i] == null){
  	     System.out.println("Row spot is full!!");
  	   }
  	   
  	   mergedRow[i] = rowToRemove[i];
	   }
	   
	   store.remove(rowToRemove);
	 
	}

}
