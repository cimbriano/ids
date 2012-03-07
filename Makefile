.PHONY: all clean

all:
	cd src; make
	cd src/ids; make
	cd src/parser; make

clean:
	cd src; make clean
	cd src/parser; make clean
	cd src/ids; make clean
	cd bin; make clean