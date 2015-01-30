(ns {{name}}.core-test
  (:require [clojure.test :refer :all]
            [{{name}}.core :refer :all]))

(defn request [method uri]
  (authenticated-app
    {:scheme :http
     :request-method :method
     :uri uri}))

(deftest known-url-200
  (is (= (:status (request :get "/")) 200)))

(deftest unknown-urls-404
  (is (= (request :get "/asdf") nil)))

