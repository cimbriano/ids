package stream;

public class StreamKey
{
    final boolean isTCP;
    final boolean isUDP;
    final Integer src_port;
    final Integer dst_port;
    final String src_ip;
    final String dst_ip;

    public StreamKey(boolean isTCP, Integer src_port, Integer dst_port,
		     String src_ip, String dst_ip) {
	this.isTCP = isTCP;
	this.src_port = src_port;
	this.dst_port = dst_port;
	this.src_ip = src_ip;
	this.dst_ip = dst_ip;
	isUDP = !isTCP;
    }

}