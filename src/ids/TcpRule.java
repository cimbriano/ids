package ids;

public class TcpRule implements AbstractRule {
	int srcPort;
	int dstPort;
	int ip;
	boolean isSend;
	String sendRecvContents;
}
