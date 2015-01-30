(ns {{name}}.web
  (:require [ring.middleware.defaults :as ring-defaults]))

(defn ok [body]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body body})

(defn compose-handlers [& handlers]
  (fn [request]
    (some (fn [handler] (handler request))
          handlers)))

(defn add-defaults-to-handler [handler]
  (-> handler
      (ring-defaults/wrap-defaults 
        (-> ring-defaults/site-defaults
            (assoc :proxy true)
            (assoc-in [:security :anti-forgery] false)))
      ((fn [app]
         (fn [req]
           (if-let [resp (app req)]
             (if (nil? (:status resp)) nil resp)))))))

