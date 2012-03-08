package ids;

public class TcpRule extends AbstractRule
{
    final Integer srcPort;
    final Integer dstPort;
    final String  ip;
    final boolean isSend;
    final String  regexp;

    public TcpRule(Integer src, Integer dst, String a, boolean s, String r) {
	srcPort = new Integer(src.intValue());
	dstPort = new Integer(dst.intValue());
	ip = new String(a);
	isSend = s;
	regexp = new String(r);
    }

    public void setName(String n) {
	name = new String(n);
    }

    public void printRule() {
	System.out.print("name="+name+"\n");
	System.out.print("type=tcp_stream\n");
	System.out.print("src_port="+srcPort+"\n");
	System.out.print("dst_port="+dstPort+"\n");
	System.out.print("ip="+ip+"\n");
	System.out.print((isSend ? "send=" : "recv=")+regexp+"\n");
    }

}
