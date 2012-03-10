CPATH = ../../bin:../../lib
BLD = ../../bin
SRC = .
PKG = $(BLD)/ids

JAVAOPTS = -classpath $(CPATH) -d $(BLD)
JAVAC = javac

CLASSES = $(PKG)/AbstractStream.class $(PKG)/IDS.class $(PKG)/IDSListener.class $(PKG)/IDSScanner.class $(PKG)/TCPStream.class $(PKG)/UDPStream.class

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv $(CLASSES)

$(PKG)/%.class: %.java
	$(JAVAC) $(JAVAOPTS) $<