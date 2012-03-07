CLASSES = ParsingRules.class

BLD = ../../build
SRC = ../src
CPATH = $(BLD):../../lib

JAVAOPTS = -classpath $(CPATH)
JAVAC = javac
JAVA = java

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv *.class

%.class: %.java
	$(JAVAC) $(JAVAOPTS) $<