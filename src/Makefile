JFLEX = ../lib/jflex/bin/jflex
CPATH = ../lib
BLD = ./parse
SRC = .

JAVAOPTS = -classpath $(CPATH)
JAVA = java

JCUP = java_cup.Main

CLASSES = $(BLD)/parser.java $(BLD)/lexer.java

.PHONY: all clean

all: $(CLASSES)
	cd out; make
	cd stream; make
	cd def; make
	cd parse; make
	cd ids; make

clean:
	cd out; make clean
	cd stream; make clean
	cd def; make clean
	cd parse; make clean
	cd ids; make clean

$(BLD)/parser.java: yrules.cup
	$(JAVA) $(JAVAOPTS) $(JCUP) yrules.cup
	mv parser.java sym.java $(BLD)

$(BLD)/lexer.java: lrules.flex
	$(JFLEX) -d $(BLD) lrules.flex
