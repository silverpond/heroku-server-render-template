(ns {{name}}.auth
  (:require [bidi.ring :as bidi]
            [hiccup.core :as hiccup]
            [cemerick.friend :as friend]
            [cemerick.friend [workflows :as workflows]
                             [credentials :as creds]]
            [ring.util.response :as resp]
            [{{name}}.web :as web]
            [{{name}}.db :as db]
            [korma.core :as korma]))

(def routes
  ["/" {"login"      :login
        "logout"     :logout
        "role-user"  :role-user
        "role-admin" :role-admin
        "needs-auth" :needs-auth}])

(def login-form
  [:div
   [:h3 "Login"]
   [:form {:method "POST" :action "login"}
    [:div "Username" [:input {:type "text" :name "username"}]]
    [:div "Password" [:input {:type "password" :name "password"}]]
    [:div [:input {:type "submit" :value "Login"}]]]])

(def resources
  {:login      (fn [req] (web/ok (hiccup/html login-form)))
   :logout     (fn [req] (friend/logout* (resp/redirect (str (:context req) "/"))))
   :role-user  (fn [req] (friend/authorize #{::user} (web/ok "You're a user!")))
   :role-admin (fn [req] (friend/authorize #{::admin} (web/ok "You're an admin!")))  
   :needs-auth (fn [req] (friend/authenticated (web/ok "Thanks for authenticating!")))})

(def auth-endpoints (bidi/make-handler routes resources))

(defn assign-role [{:keys [role] :as user}]
  (assoc user :roles #{(keyword "{{name}}.auth" role)}))

(defn find-user [email]
  (assign-role (first (korma/select db/users (korma/where {:email email})))))

(derive ::admin ::user)

(defn add-authentication-to-handler [handler]
  (let [handle-with-auth (web/compose-handlers auth-endpoints handler)]
    (friend/authenticate
      handle-with-auth
      {:allow-anon? true
       :login-uri "/login"
       :default-landing-uri "/"
       :unauthorized-handler
       #(-> (hiccup/html [:p "You do not have sufficient privileges to access " (:uri %)])
            resp/response
            (resp/content-type "text/html")
            (resp/status 401))
       :credential-fn #(creds/bcrypt-credential-fn find-user %)
       :workflows [(workflows/interactive-form)]})))

