import java_cup.runtime.*;

%%

%class lexer
%line
%column

%cup

%{
    private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value);
    }
%}

string = [A-Za-z0-9 ]+
octet = [0-255]
ip = {octet}\.{octet}\.{octet}\.{octet}
port = [0-65535]
regexp = {string}
flag = S|A|F|R|P|U
flags = {flag}*

%%

"flags"         { return symbole(sym.SET_FLAGS); }
"with"          { return symbol(sym.WITH); }
"udp"		{ return symbol(sym.UDP); }
"tcp"		{ return symbol(sym.TCP); }
"proto="	{ return symbol(sym.SET_PROTO); }
"send="		{ return symbol(sym.SEND); }	
"recv="		{ return symbol(sym.RECV); }
"type="		{ return symbol(sym.SET_TYPE); }
"ip="		{ return symbol(sym.SET_IP); }
"any"		{ return symbol(sym.ANY); }	
"src_port="	{ return symbol(sym.SET_DST); }
"dst_port=" 	{ return symbol(sym.SET_SRC); }
"protocol"	{ return symbol(sym.PROTOCOL); }
"tcp_stream" 	{ return symbol(sym.TCP_STREAM); }
"host="		{ return symbol(sym.SET_HOST); }
"name="		{ return symbol(sym.SET_NAME); }
"\n"		{ return symbol(sym.NLINE); } 

