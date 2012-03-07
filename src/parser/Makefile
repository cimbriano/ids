CPATH = ../../lib:.

BLD = ../../bin
SRC = .

JAVAOPTS = -classpath $(CPATH) -d $(BLD)
JAVAC = javac

CLASSES = $(BLD)/parser.class $(BLD)/lexer.class

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv $(CLASSES)

$(BLD)/%.class: %.java
	$(JAVAC) $(JAVAOPTS) $<