(ns rest-poc.service.service
  (:require [rest_poc.model.model]
            [rest-poc.database.mongo :refer [add-to-mongo!]])
  (:import (rest_poc.model.model Person)))

(def people "Varias people")

(defn add-person [^Person person]
  (add-to-mongo! person))