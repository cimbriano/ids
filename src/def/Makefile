CPATH = ../../bin:../../lib/jpcap/jpcap.jar
BLD = ../../bin
SRC = .
PKG = $(BLD)/def

JAVAOPTS = -classpath $(CPATH) -d $(BLD)
JAVAC = javac

CLASSES = $(PKG)/AbstractRule.class $(PKG)/Rule.class $(PKG)/RuleList.class \
          $(PKG)/ProtocolSubrule.class $(PKG)/ProtocolSubruleList.class \
          $(PKG)/TCPProtocolRule.class $(PKG)/TcpRule.class $(PKG)/UDPProtocolRule.class \
          $(PKG)/ThreatDefinition.class

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv $(CLASSES)

$(PKG)/%.class: %.java
	$(JAVAC) $(JAVAOPTS) $<
