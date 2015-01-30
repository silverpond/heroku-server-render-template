(ns {{name}}.controllers
  (:require [bidi.ring :as bidi]
            [hiccup.core :as hiccup]
            [cemerick.friend :as friend]
            [{{name}}.web :as web]))

(def routes
  ["/" {"" :index}])

(defn index [req]
  (web/ok
    (hiccup/html
        [:div
         [:ul
          [:li [:a {:href "/login"} "Login"]]
          [:li [:a {:href "/logout"} "Logout"]]
          [:li [:a {:href "/role-user"} "Role User"]]
          [:li [:a {:href "/role-admin"} "Role Admin"]]
          [:li [:a {:href "/needs-auth"} "Needs Auth"]]]
         [:p (if-let [identity (friend/identity req)]
              (apply str "Logged in, with these roles: "
                     (-> identity friend/current-authentication :roles))
              "anonymous user")]])))

(def resources
  {:index index})

(def handler
  (bidi/make-handler routes resources))

