(ns rest-basics.core
  (:require
    [ring.adapter.jetty :refer [run-jetty]]
    [ring.middleware.params :as p]
    [rest-basics.middleware :as m]
    [rest-basics.routes :as r]
  ))

(def app
  (-> r/routes
      m/logger
      m/req-res-displayer
      p/wrap-params))

(defonce server
  (run-jetty #'app {:port 8080 :join? false}))