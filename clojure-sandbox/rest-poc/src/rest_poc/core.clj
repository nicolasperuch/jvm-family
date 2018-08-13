(ns rest-poc.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :refer [created response]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [compojure.route :as route]
            [compojure.core :refer [defroutes GET POST]]
            [rest-poc.service.service :as service]
            ))

(defroutes app-routes
  (GET  "/" [] "Home page")
  (GET  "/people" [] (str service/people))
  (POST "/person" {body :body} (service/add-person body))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-json-body {:keywords? true})
      (wrap-json-response {:keywords? true})))

(defn -main []
  (jetty/run-jetty app {:port 8080}))