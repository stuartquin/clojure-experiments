(ns selected.core
  (:use [org.httpkit.server :only [run-server]])
  (:import [java.io PushbackReader])
  (:require [ring.middleware.reload :as reload]
            [clojure.java.io :as io]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [selected.mysql :as db]
            [selected.persist :as persister]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [compojure.core :refer [defroutes GET POST]]))

(defn run-query [req]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body {
     :id (persister/create-session ((:body req) "query"))
     :results (db/run-query ((:body req) "query"))
   }})

(defn fetch-query [req]
  (let [id (:id (:route-params req))]
    {:status  200
     :headers {"Content-Type" "application/json"}
     :body {:id id :query (persister/get-session id)}}))

(defroutes app-routes
  (POST "/query" [] run-query)
  (GET "/query/:id" [id] fetch-query)
  (route/not-found "Not Found"))

(defn -main [& args] ;; entry point, lein run will pick up and start from here
  (let [handler (-> (site #'app-routes)
                    reload/wrap-reload
                    wrap-json-body
                    wrap-json-response)]
    (run-server handler {:port 3000})))
