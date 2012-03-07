.PHONY: all clean

all:
	cd src; make
	cd src/parser; make

clean:
	cd src; make clean
	cd src/parser; make clean
	cd bin; make clean