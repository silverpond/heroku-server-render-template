(ns {{name}}.db
  (:require [korma.db :refer :all]
            [korma.core :refer :all]
            [clojure.string :as string])
  (:import (java.net URI)))

(defn- convert-db-uri [db-uri]
  (let [{:keys [host port userInfo path]} (bean (java.net.URI. db-uri))
        [user pass] (if userInfo (string/split userInfo #":") [nil nil])
        db (subs path 1)]
  {:port port :host host :user user :password pass :db db}))

(defdb dev
  (postgres (convert-db-uri (System/getenv "DATABASE_URL"))))

(defentity users)

