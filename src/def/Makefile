CPATH = ../../bin:../../lib/jpcap/jpcap.jar
BLD = ../../bin
SRC = .
PKG = $(BLD)/def

JAVAOPTS = -classpath $(CPATH) -d $(BLD)
JAVAC = javac

CLASSES = $(PKG)/AbstractRule.class \
          $(PKG)/Rule.class \
          $(PKG)/AbstractProtocolRuleChecker.class $(PKG)/TCPProtocolRuleChecker.class $(PKG)/UDPProtocolRuleChecker.class \
          $(PKG)/ProtocolSubrule.class \
          $(PKG)/ProtocolSubruleList.class \
          $(PKG)/TCPProtocolRule.class \
          $(PKG)/UDPProtocolRule.class \
          $(PKG)/RuleList.class \
          $(PKG)/TCPStreamRule.class \
          $(PKG)/ThreatDefinition.class \
          $(PKG)/RuleAlert.class

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv $(CLASSES)

$(PKG)/%.class: %.java
	$(JAVAC) $(JAVAOPTS) *.java

