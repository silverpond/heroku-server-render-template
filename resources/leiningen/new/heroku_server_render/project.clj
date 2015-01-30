(defproject {{name}} "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [http-kit "2.1.16"]
                 [korma "0.3.0"]
                 [org.postgresql/postgresql "9.3-1102-jdbc41"]
                 [log4j "1.2.15" :exclusions  [javax.mail/mail
                                               javax.jms/jms
                                               com.sun.jdmk/jmxtools
                                               com.sun.jmx/jmxri]]
                 [bidi "1.12.0"]
                 [hiccup "1.0.5"]
                 [com.cemerick/friend "0.2.1"]
                 [ring/ring-defaults "0.1.3"]]
  :plugins  [[clj-sql-up "0.3.3"]]
  :main ^:skip-aot {{name}}.core
  :min-lein-version "2.5.0"
  :target-path "target/%s"
  :uberjar-name "{{name}}-standalone.jar"
  :profiles {:uberjar {:aot :all}}
  
  :clj-sql-up {:database  ~(str "jdbc:" (System/getenv "DATABASE_URL"))
               :deps [[org.postgresql/postgresql "9.3-1100-jdbc4"]]})
