(ns todo-app.server
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import [org.bson.types ObjectId]
           [com.mongodb DB WriteConcern]))

;; localhost, default port
(let [conn (mg/connect)
      db   (mg/get-db conn "clojure-test")]
  ;; with a generated document id, returns the complete
  ;; inserted document
  (mc/insert-and-return db "clojure" {:name "John" :age 30})

  ;; with explicit document id (recommended)
  (mc/insert db "clojure" { :_id (ObjectId.) :first_name "John" :last_name "Lennon" })

  ;; multiple documents at once
  (mc/insert-batch db "clojure" [{ :first_name "John" :last_name "Lennon" }
                                   { :first_name "Paul" :last_name "McCartney" }])

  ;; without document id (when you don't need to use it after storing the document)
  (mc/insert db "clojure" { :first_name "John" :last_name "Lennon" })

  ;; with a different write concern
  (mc/insert db "clojure" { :_id (ObjectId.) :first_name "John" :last_name "Lennon" } WriteConcern/JOURNAL_SAFE))