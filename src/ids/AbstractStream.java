package ids;

import java.util.Iterator;

public interface AbstractStream extends Iterable<IPPacket> {
	public boolean isTCP();
	public boolean isUDP();
}
