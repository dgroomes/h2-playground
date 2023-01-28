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


## Overview

This codebase is my way of learning H2 and building a corpus of runnable reference code.

**NOTE**: This project was developed on macOS. It is for my own personal use.


## Standalone sub-projects

This repository illustrates different concepts, patterns and examples via standalone sub-projects. Each sub-project is
completely independent of the others and do not depend on the root project. This _standalone sub-project constraint_
forces the sub-projects to be complete and maximizes the reader's chances of successfully running, understanding, and
re-using the code.

The sub-projects include:


### `basic/`

A simple Java program that embeds an in-memory H2 database.

See the README in [basic/](basic/).


### `advanced/`

An intermediate H2 example program showcasing "server mode", logging, and custom Java stored procedures (stored procs not implemented).


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [ ] IN PROGRESS Showcase H2 logging config (I always want to know how to do logging to help observe a sophisticated system, like
  a database and database client).
* [ ] Consider running H2 as a server (long-running process). I think this is a good idea because it will allow me to connect to the database
  from a GUI client (Intellij) and explore the db like I normally would.
* [ ] Do something with Java-defined functions as stored procedures. This is a killer feature of H2, is that it is
  itself a Java library so it interops perfectly with custom Java code.


## Reference

* [H2 website](https://h2database.com/)
* [GitHub repo: `h2database/h2database`](https://github.com/h2database/h2database)
