package ids;

import net.sourceforge.jpcap.net.IPPacket;

public interface AbstractStream extends Iterable<IPPacket> {
	public boolean isTCP();
	public boolean isUDP();
	public void add(IPPacket packet);
}
