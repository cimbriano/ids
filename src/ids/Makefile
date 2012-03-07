CPATH = ../../lib:../../bin:.

BLD = ../../bin
SRC = .

JAVAOPTS = -classpath $(CPATH) -d $(BLD)
JAVAC = javac

CLASSES = $(BLD)/AbstractRule.class $(BLD)/AbstractRuleList.class $(BLD)/ProtocolRule.class $(BLD)/ProtocolSubrule.class $(BLD)/SortedProtocolSubrulList.class $(BLD)/TcpRule.class $(BLD)/ThreatDefinition.class

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv $(CLASSES)

$(BLD)/%.class: %.java
	$(JAVAC) $(JAVAOPTS) $<