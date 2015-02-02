(ns leiningen.new.heroku-server-render
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "heroku-server-render"))

(defn heroku-server-render [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' heroku-server-render project.")
    (->files data
             [".env" (render ".env" data)]
             ["Procfile" (render "Procfile" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]

             ["migrations/20150108160545457-create-users-table.clj" (render "migrations/20150108160545457-create-users-table.clj" data)]
             ["migrations/20150129151956512-create-admin-user.clj" (render "migrations/20150129151956512-create-admin-user.clj" data)]

             ["resources/public/.gitkeep" (render "resources/public/.gitkeep" data)]

             ["scripts/deploy" (render "scripts/deploy" data) :executable true]
             ["scripts/repl" (render "scripts/repl" data) :executable true]
             ["scripts/server" (render "scripts/server" data) :executable true]

             ["scripts/db/console" (render "scripts/db/console" data) :executable true]
             ["scripts/db/create" (render "scripts/db/create" data) :executable true]
             ["scripts/db/start" (render "scripts/db/start" data) :executable true]

             ["scripts/db/migrations/create" (render "scripts/db/migrations/create" data) :executable true]
             ["scripts/db/migrations/rollback" (render "scripts/db/migrations/rollback" data) :executable true]
             ["scripts/db/migrations/run" (render "scripts/db/migrations/run" data) :executable true]

             ["src/log4j.xml" (render "src/log4j.xml" data)]

             ["src/{{sanitized}}/auth.clj" (render "src/project/auth.clj" data)]
             ["src/{{sanitized}}/controllers.clj" (render "src/project/controllers.clj" data)]
             ["src/{{sanitized}}/core.clj" (render "src/project/core.clj" data)]
             ["src/{{sanitized}}/db.clj" (render "src/project/db.clj" data)]
             ["src/{{sanitized}}/web.clj" (render "src/project/web.clj" data)]

             ["test/{{sanitized}}/core_test.clj" (render "test/project/core_test.clj" data)]
             )))
