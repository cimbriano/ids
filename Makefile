.PHONY: all clean

all:
	cd src; make
	cd build; make

clean:
	cd src; make clean
	cd build; make clean