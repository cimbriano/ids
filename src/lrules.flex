import java_cup.runtime.*;

%%

%class lexer

%line
%column

%cup

 /* Helper functions */

%{
    private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value);
    }
%}

/* Grammar terminals */

port = (6[0-5][0-5][0-3][0-5]) | ([0-5][0-9][0-9][0-9][0-9]) |
       ([0-9][0-9][0-9][0-9])  | ([0-9][0-9][0-9]) |
       ([0-9][0-9]) | ([0-9])

octet = (2[0-5][0-5]) | ([0-1][0-9][0-9]) | ([0-9][0-9]) | ([0-9])

ip = {octet}\.{octet}\.{octet}\.{octet}

string = [A-Za-z0-9 ]+

regexp = \".*\"

flag = [S|A|F|R|P|U]

newline = \r|\n|\r\n

whitespace = [ \t\f]

%%

" with flags="  { return symbol(sym.WITH_FLAGS); }
"udp"		{ return symbol(sym.UDP); }
"tcp"		{ return symbol(sym.TCP); }
"proto="	{ return symbol(sym.SET_PROTO); }
"send="		{ return symbol(sym.SEND); }	
"recv="		{ return symbol(sym.RECV); }
"type="		{ return symbol(sym.SET_TYPE); }
"ip="		{ return symbol(sym.SET_IP); }
"any"		{ return symbol(sym.ANY); }	
"src_port="	{ return symbol(sym.SET_SRC); }
"dst_port=" 	{ return symbol(sym.SET_DST); }
"protocol"	{ return symbol(sym.PROTOCOL); }
"tcp_stream" 	{ return symbol(sym.TCP_STREAM); }
"host="		{ return symbol(sym.SET_HOST); }
"name="		{ return symbol(sym.SET_NAME); }

{whitespace}    { /* just skip */ }

{port}		{ return symbol(sym.PORT, new Integer(yytext())); }
{ip}		{ return symbol(sym.IP, new String(yytext())); }
{string}	{ return symbol(sym.STRING, new String(yytext())); }
{regexp}        { return symbol(sym.REGEXP, new String(yytext())); }
{newline}       { return symbol(sym.NLINE); } 

[^]             { throw new Error("Illegal character <" + yytext() + ">"); }


