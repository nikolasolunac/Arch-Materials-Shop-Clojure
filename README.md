# materials-shop-clj

A Clojure project designed with Leiningen that presents shop for architectural materials, with buyers and items. It uses korma for MySQL communication, ring as a web server, composure-api as a REST API framework and lein/ring for binding with Leiningen. For API testing, this project uses Swagger.

## prerequisites

This project uses Clojure 1.9.0 on Java 1.8.0 and it is developed in the Windows OS with mandatory extension: Chocolatey package manager. Database for this app is in 'baza/' folder and can be executed in MySQL. Visual Studio Code was used for writting code and it is recommended IDE for this project.

## run

To run the app use 'lein ring server' in the root of the project (this will open Swagger API).

## architecture

/baza/ - holds latest database;

/src/dbbroker/ - contains individual entities related to SQL logic;

/src/dbkonekcija/ - provides a connection to the MySQL server;

/src/domen/ - holds the domain entities (same as ones in database tables);

/src/kontroler/ - contains application logic (API CRUD routes);

/src/util/ - utility helpers.

## libraries

[lein-ring "0.12.4"] - Ring server plugin (Leiningen);

[compojure "1.6.1"] - Routing library for Ring/Clojure;

[metosin/compojure-api "2.0.0-alpha23"] - Composure library for development of REST APIs;

[korma "0.4.3"] - SQL helper library;

[ring/ring-core "1.7.0-RC2"] ,[ring/ring-jetty-adapter "1.7.0-RC2"], [ring/ring-json "0.4.0"], [ring/ring-defaults "0.3.2"] - Ring web server dependent libraries;

[mysql/mysql-connector-java "5.1.6"] - Standard MySQL connector;

[clj-http "3.9.1"] - Lower-level dependency of Composure and Swagger.

## License

Copyright © 2018 Nikola Solunac

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
