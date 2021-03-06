(ns todo-app.handler
  (:require [compojure.coercions :refer [as-int]]
            [compojure.core :refer [defroutes GET POST DELETE]]
            [compojure.route :as route]
            [hiccup.page :refer [doctype include-css]]
            [hiccup2.core :refer [html]]
            [hiccup.form :as f]
            [hiccup.element :refer [link-to]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.anti-forgery :refer [*anti-forgery-token*]]
            [ring.util.response :refer [redirect]]
            [todo-app.domain :refer [all-todos todo-by-id add-todo! remove-todo! add-to-mongo!]]))

(defn page [title & content]
  (str
    (html
      (doctype :html5)
      [:html
       [:head
        [:title title]
        (include-css "splendor.css")]
       [:body [:h1 title] content]])))

(defn index []
  (page "TODO App"
        [:ul
         (for [todo (all-todos)]
           [:li (link-to (str "/" (:id todo)) (:text todo))])]))

(defn add-todo [todo]
  (when (not (clojure.string/blank? todo))
    (add-to-mongo! todo))
  (redirect "/"))

(defroutes app-routes
           (GET  "/" [] (index))
           (POST "/" [todo] (add-todo todo))
           (GET  "/:id" [id :<< as-int] ("" id))
           (DELETE "/:id" [id :<< as-int] ("" id))
           (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes (site-defaults [:security :anti-forgery] false)))