(ns another-rest-api.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [compojure.route :as route]))

(def app
  (-> app-routes
      (middleware/wrap-json-body {:keywords? true})
      middleware/wrap-json-response))

(defroutes app-routes
           (POST "/" request
             (let [name (or (get-in request [:params :name])
                            (get-in request [:body :name])
                            "John Doe")]
               {:status 200
                :body {:name name
                       :desc (str "The name you sent to me was " name)}}))
           (route/resources "/")
           (route/not-found "Not Found"))