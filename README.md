# h2-playground

📚 Learning and exploring H2 (the Java-based embedded SQL database).

> The main features of H2 are:
>
> * Very fast, open source, JDBC API
> * Embedded and server modes; in-memory databases
> * Browser based Console application
> * Small footprint: around 2.5 MB jar file size
>
> -- <cite> https://h2database.com/html/main.html <cite>


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


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [ ] Showcase H2 logging config (I always want to know how to do logging to help observe a sophisticated system, like
  a database and database client).
* [ ] Consider running H2 as a server. I think this is a good idea because it will allow me to connect to the database
  from a GUI client (Intellij) and explore the db like I normally would.
* [ ] Do something with Java-defined functions as stored procedures. This is a killer feature of H2, is that it is
  itself a Java library so it interops perfectly with custom Java code.


## Reference

* [H2 website](https://h2database.com/)
* [GitHub repo: `h2database/h2database`](https://github.com/h2database/h2database)
