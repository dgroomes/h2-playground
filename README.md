# h2-playground

📚 Learning and exploring H2 (the Java-based embedded SQL database).

> The main features of H2 are:
>
> * Very fast, open source, JDBC API
> * Embedded and server modes; in-memory databases
> * Browser based Console application
> * Small footprint: around 2.5 MB jar file size
>
> -- <cite>https://h2database.com/html/main.html<cite>


## Description

This project implements a runnable Java program that embeds an H2 database.


### Instructions

Follow these instructions to build and run the program:

1. Use Java 17
2. Run the program:
   * ```shell
     ./gradlew run
     ```
   * Altogether, it will look something like this:
     ```text
     $ ./gradlew run
     
     > Task :run
     18:33:29 [main] INFO dgroomes.App - Found this observation: Observation[id=1, observation=The sky is blue]
     18:33:29 [main] INFO dgroomes.App - Found this observation: Observation[id=2, observation=The speed of light can circle the earth 7 times in a second]
     ```


## Reference

* [H2 website](https://h2database.com/)
* [GitHub repo: `h2database/h2database`](https://github.com/h2database/h2database)
