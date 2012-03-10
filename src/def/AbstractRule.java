package def;

import stream.*;

interface AbstractRule {
	
   public void printRule();
   
   //TODO Maybe return an object with alert info 
   public void scan(AbstractStream stream);

}
