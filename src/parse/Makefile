CPATH = ../../bin:../../lib
BLD = ../../bin
SRC = .
PKG = $(BLD)/parse

JAVAOPTS = -classpath $(CPATH) -d $(BLD)
JAVAC = javac

CLASSES = $(PKG)/sym.class $(PKG)/lexer.class $(PKG)/parser.class

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv $(CLASSES)

$(PKG)/%.class: %.java
	$(JAVAC) $(JAVAOPTS) $<