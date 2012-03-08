CPATH = ../../lib:../../bin:.
BLD = ../../bin
SRC = .
PKG = $(BLD)/ids

JAVAOPTS = -classpath $(CPATH) -d $(BLD)
JAVAC = javac

CLASSES = $(PKG)/AbstractRule.class $(PKG)/AbstractRuleList.class \
          $(PKG)/ProtocolSubrule.class $(PKG)/ProtocolRule.class \
          $(PKG)/SortedProtocolSubruleList.class $(PKG)/TcpRule.class \
          $(PKG)/ThreatDefinition.class $(PKG)/Subrule.class

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv $(CLASSES)

$(PKG)/%.class: %.java
	$(JAVAC) $(JAVAOPTS) $<