(ns rest-basics.routes
  (:require
    [compojure.core :refer [defroutes GET POST]]
    [compojure.route :refer [not-found]]
    [rest-basics.views :as v]
  ))

(defroutes routes
  (GET "/" req (v/main req)
  ;(GET "/get-submit" req (v/display-result req))
  ;(POST "/create" req (v/display-result req))
               ))
