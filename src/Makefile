CLASSES = parser.java lexer.java

JFLEX = ../lib/jflex/bin/jflex
CPATH = ../lib:.

JAVAOPTS = -classpath $(CPATH)
JAVA = java

BLD = ./parser
SRC = .

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv ./parser/*.java

parser.java: yrules.cup
	$(JAVA) $(JAVAOPTS) java_cup.Main yrules.cup
	mv parser.java sym.java ./parser

lexer.java: lrules.flex
	$(JFLEX) -d $(BLD) lrules.flex
