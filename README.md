# heroku-server-render

A Leiningen template for creating web applications intended to be hosted on
Heroku using Postgres for storage.

It includes:

* Bidi for request routing.
* Hiccup for server side html templating.
* Friend for authentication and authorisation.
* Korma for database queries.
* Clj sql up for database migrations.
* Http-kit for serving web.

Additionally foreman is used for configuration and environments.

## Usage

For development first set up the database.

```sh
foreman run ./scripts/db/start
foreman run ./scripts/db/create
foreman run ./scripts/db/migrations/run
```

Then start a repl and connect from your editor.

```sh
foreman run ./scripts/repl
```

There's a comment in `core.clj` that you can execute to get a dev server
running.

```clojure
(in-ns 'your-project.core)
(server)
(def server (-main "8080"))
```

## License

Copyright © 2015 Silverpond Pty Ltd

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
