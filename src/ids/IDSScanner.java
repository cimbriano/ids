package ids;

import stream.*;
import def.*;

public class IDSScanner
{
    final ThreatDefinition threat;
	
    public IDSScanner(ThreatDefinition t){
	threat = t;
    }

    public void scan(AbstractStream stream) {
	for (Rule r : threat)
	    r.scan(stream);
    }

}
