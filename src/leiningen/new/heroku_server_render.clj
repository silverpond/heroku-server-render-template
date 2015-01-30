(ns leiningen.new.heroku-server-render
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "heroku-server-render"))

(defn heroku-server-render
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' heroku-server-render project.")
    (->files data
             ["src/{{sanitized}}/foo.clj" (render "foo.clj" data)])))
