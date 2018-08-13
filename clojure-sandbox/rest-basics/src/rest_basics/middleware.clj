(ns rest-basics.middleware
  (:require [ring.util.response :as res]
            [ring.util.request :as req]))

(defonce log-atom (atom []))

(defn logger [handler]
  (fn [req]
    (let [res (handler req)]
      (swap! log-atom #(conj % {:request req :response res}))
      res)))

(defn req-res-displayer [handler]
  (fn [req]
    (let [res (handler req)]
      (println "\n Request: ")
      (println req)
      (println "\n Response: ")
      (println res)
      res)))