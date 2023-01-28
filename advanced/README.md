# advanced

An intermediate H2 example program showcasing "server mode", logging, and custom Java stored procedures (stored procs not implemented).


## Overview

Can we run an H2 database in server mode so that other clients (like our favorite database IDE) can connect to it? This
is stretching the limits of what we usually use H2 for, but I want to exercise it.


## Instructions

Follow these instructions to build and run the program:

1. Use Java 17
2. Build the program distribution:
    * ```shell
      ./gradlew install
      ```
3. Run the program (it will run H2):
    * ```shell
      ./build/install/advanced/bin/advanced
      ```
    * Study the output. While the logs aren't the highest quality, they do tell a good story. Here are some highlights.
    *  ```text
       23:34:11 [main] INFO h2database - mydb:database opening /Users/davidgroomes/repos/personal/h2-playground/advanced/mydb (build 214)
       ...
       23:34:11 [main] INFO dgroomes.advanced.App - The database file should have been created. Look for a file with the file extension '.mv.db' (this is an H2 thing) in the  directory /Users/davidgroomes/repos/personal/h2-playground/advanced
       ...
       23:34:11 [main] INFO h2database - mydb:database closed
       ...
       23:34:11 [H2 TCP Server (tcp://localhost:8084) thread-1] INFO h2database - mydb:database opening
       ...
       23:34:11 [main] INFO dgroomes.advanced.App - Found this observation: Observation[id=1, observation=The sky is blue]
       23:34:11 [main] INFO dgroomes.advanced.App - Found this observation: Observation[id=2, observation=The speed of light can circle the earth 7 times in a second]
       ...
       23:34:11 [H2 TCP Server (tcp://localhost:8084) thread-1] INFO h2database - mydb:database closed
       ```
4. Connect via your favorite JDBC client
    * I personally use Intellij Ultimate to connect to and interact with databases.
    * Connect using the JDBC URL: `jdbc:h2:tcp://localhost:8084/mydb`


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [ ] Do something with Java-defined functions as stored procedures. This is a killer feature of H2, is that it is
  itself a Java library so it interops perfectly with custom Java code.
