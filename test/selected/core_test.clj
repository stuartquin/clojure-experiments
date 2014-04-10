(ns selected.core-test
  (:use midje.sweet)
  (:require [selected.core :as core]
            [selected.mysql :as db]
            [selected.persist :as persister]))

(facts "run-query"
  (fact "Returns list of results"
    (core/run-query {:body {"query" "test"}})
      => {:status 200
          :headers {"Content-Type" "application/json"}
          :body {:id [1] :results [1,2,3]}} 
    (provided 
      (db/run-query "test") => [1,2,3])
    (provided 
      (persister/create-session "test") => [1])))

(facts "fetch-query"
  (fact "Returns query"
    (core/fetch-query {:route-params {:id 1}})
      => {:status 200
          :headers {"Content-Type" "application/json"}
          :body {:id 1 :query "SELECT test FROM test"}}
    (provided 
      (persister/get-session 1) => "SELECT test FROM test")))
