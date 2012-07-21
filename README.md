iwtr-print-deps
===============

Prints the dependency tree using data loaded by iwtr-import

This command-line tool is part of the IWTR project.

iwtr-print-deps prints a list of dependent modules, grouped by distance starting from the
module you pass as a command line parameter.
The group is derived from the data previously loaded into the graph database using the
tool iwtr-import.

How to make it work
-------------------

1. Download the code
2. Open a terminal and go to the project root directory

3. On a Linux machine:    
    ./gradlew installApp    
    cd build/install/iwtr-print-deps    
    ./bin/iwtr-print-deps
	
    On a Windows machine:    
    gradlew.bat installApp    
    cd build\install\iwtr-print-deps    
    bin\iwtr-print-deps
    
4. Follow the instructions
5. The command won't give you any feedback if the module you're looking for doesn't exist in the database.
   Il will complain only if the default location ($HOME/.iwtr) of the database is missing or no database is found.

Future enhancements 
-------------------

* Testing? Probably not much unit tests but most likely some integration tests for
   avoiding regressions