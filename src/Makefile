CLASSES = parser.java lexer.java

JFLEX = ../lib/jflex/bin/jflex
CPATH = ../lib:.

JAVA = java

BLD = ../build
SRC = .

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv *.java

parser.java: yrules.cup
	$(JAVA) -classpath $(CPATH) java_cup.Main yrules.cup

lexer.java: lrules.flex
	$(JFLEX) lrules.flex
