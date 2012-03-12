CPATH = ../../bin:../../lib:../../lib/jpcap/jpcap.jar
BLD = ../../bin
SRC = .
PKG = $(BLD)/stream

JAVAOPTS = -classpath $(CPATH) -d $(BLD) -Xlint:unchecked
JAVAC = javac

CLASSES = $(PKG)/TCPStreamTuple.class \
          $(PKG)/TCPStream.class

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv $(CLASSES)

$(PKG)/%.class: %.java
	$(JAVAC) $(JAVAOPTS) $<
