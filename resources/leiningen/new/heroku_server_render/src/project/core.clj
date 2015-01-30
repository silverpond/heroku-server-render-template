(ns {{name}}.core
  (:require [org.httpkit.server :as httpkit]
            [{{name}}.auth :as auth]
            [{{name}}.controllers :as controllers]
            [{{name}}.web :as web])
  (:gen-class))

(def authenticated-app
  (-> #'controllers/handler
      auth/add-authentication-to-handler
      web/add-defaults-to-handler))

(defn -main [& [port]]
  (let [port   (Integer/parseInt port)
        config {:port port}]
    (httpkit/run-server authenticated-app config)))

(comment

  (in-ns '{{name}}.core)
  (server)
  (def server (-main "8080"))

  )

