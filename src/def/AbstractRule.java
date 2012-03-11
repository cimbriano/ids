package def;

import stream.*;

public interface AbstractRule
{
	
   public void printRule();
   
   //TODO Maybe return an object with alert info 
   public void scan(AbstractStream stream);

}
