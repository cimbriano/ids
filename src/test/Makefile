CPATH = ../../bin:../../lib
BLD = .
SRC = .

JAVAOPTS = -classpath $(CPATH)
JAVAC = javac

JAVA = java

CLASSES = ParsingRules.class

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv $(CLASSES)

%.class: %.java
	$(JAVAC) $(JAVAOPTS) $<