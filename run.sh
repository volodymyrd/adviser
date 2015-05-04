#!/bin/bash
#java -cp target/classes. -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n com.gmail.volodymyrdotsenko.pokerstat.shell.Shell
java -cp target/adviser-1.0-SNAPSHOT.jar:target/lib/*.jar -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n com.gmail.volodymyrdotsenko.adviser.Console