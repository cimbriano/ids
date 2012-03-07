CLASSES = parser.class lexer.class

CPATH = ../lib:.
SRC = ../src
BLD = .

JAVAOPTS = -classpath $(CPATH) -sourcepath $(SRC) -d $(BLD)
JAVAC = javac
JAVA = java

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv *.class

%.class: $(SRC)/%.java
	$(JAVAC) $(JAVAOPTS) $<