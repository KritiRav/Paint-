# super simple makefile
# call it using 'make NAME=name_of_code_file_without_extension'
# (assumes a .java extension)
NAME = "main.java widget.java mainMenuView.java ColorChooserDemo.java Lines2.java"
NAME_NO_EXT = "main widget mainMenuView ColorChooserDemo Lines2"

all:
	@echo "Compiling..."
	javac main.java widget.java mainMenuView.java ColorChooserDemo.java Lines2.java

run: all
	@echo "Running..."
	java main widget mainMenuView ColorChooserDemo Lines2

clean:
	rm -rf *.class