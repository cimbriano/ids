package def;

import ids.AbstractStream;

interface AbstractRule {
	
   public void printRule();
   
   //TODO Maybe return an object with alert info 
   public void scan(AbstractStream stream);
    /* scan packet, etc */

}
