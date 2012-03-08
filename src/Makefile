JFLEX = ../lib/jflex/bin/jflex
CPATH = ../lib:.
BLD = ./parser
SRC = .

JAVAOPTS = -classpath $(CPATH)
JAVA = java

CLASSES = $(BLD)/parser.java $(BLD)/lexer.java

.PHONY: all clean

all: $(CLASSES)
	cd ids; make
	cd parser; make

clean:
	rm -fv $(CLASSES)
	cd ids; make clean
	cd parser; make clean

$(BLD)/parser.java: yrules.cup
	$(JAVA) $(JAVAOPTS) java_cup.Main yrules.cup
	mv parser.java sym.java $(BLD)

$(BLD)/lexer.java: lrules.flex
	$(JFLEX) -d $(BLD) lrules.flex
