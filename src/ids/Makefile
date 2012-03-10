CPATH = ../../bin:../../lib:../../lib/jpcap/jpcap.jar
BLD = ../../bin
SRC = .
PKG = $(BLD)/ids

JAVAOPTS = -classpath $(CPATH) -d $(BLD)
JAVAC = javac

CLASSES = $(PKG)/IDS.class $(PKG)/IDSScanner.class $(PKG)/IDSListener.class

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv $(CLASSES)

$(PKG)/%.class: %.java
	$(JAVAC) $(JAVAOPTS) $<
