(defproject hello-api-clojure "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [
      [org.clojure/clojure "1.9.0"]
      [org.slf4j/jcl-over-slf4j "1.7.25"]
      [org.slf4j/log4j-over-slf4j "1.7.25"]
      [org.slf4j/jul-to-slf4j "1.7.25"]
    
      [io.pedestal/pedestal.service "0.5.4"]
      [io.pedestal/pedestal.jetty "0.5.4"]    
      [com.novemberain/monger "3.1.0"]
      [ch.qos.logback/logback-classic "1.2.3" :exclusions [org.slf4j/slf4j-api]]
  ]
  :min-lein-version "2.0.0"
  :resource-paths ["config", "resources"]
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "hello-api-clojure.server/run-dev"]}
                   :dependencies [[io.pedestal/pedestal.service-tools "0.5.4"]]}
             :uberjar {:aot [hello-api-clojure.server]}}
  :main ^{:skip-aot true} hello-api-clojure.server)

