(ns rest-poc.database.mongo
  (:require [monger.core :as mg]
            [monger.collection :as mc]))

(defn add-to-mongo! [person]
  (let [conn (mg/connect)
        db   (mg/get-db conn "clojure-test")]
    (mc/insert-and-return db "clojure" person)))