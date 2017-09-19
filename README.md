## Sire API 

An application to execute pull requests workflows with a yaml configuration file to execute automated tests and run apps with docker. 

![SHIPPING](https://media.giphy.com/media/bZBmitwUwKtDa/giphy.gif)

## Summary

The objective is practice clojure knowledge while create a little complex application to manage PRs test workflows (automated tests, black box and others).

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Install

After clonning or downloading this repository, execute the following in terminal to install the project dependencies:

    lein install

## Development

For execute this project in development it's necessary to open REPL because is using the Component to create isolated systems. Open REPL and execute the following commands to run, reset and stop the application.

    lein repl

For run dev system:

    (go)

For refresh files inside REPL:

    (reset)

For stop the application:

    (stop)


## License

The MIT License (MIT)

Copyright © 2017 Eron Rodrigues Alves