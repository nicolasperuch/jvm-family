(defproject rest-poc "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [ring/ring "1.6.3"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-mock "0.3.2"]
                 [cheshire "5.8.0"]
                 [compojure "1.6.0"]
                 [midje "1.9.1"]
                 [com.novemberain/monger "3.1.0"]]
  :plugins [[lein-midje "3.0.0"]
            [lein-ring "0.9.7"]]
  :ring {:handler rest-poc.core/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
