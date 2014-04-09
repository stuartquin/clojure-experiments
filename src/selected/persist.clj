(ns selected.persist
  (:require [taoensso.carmine :as car :refer (wcar)]))

(def server1-conn {:pool {} :spec {:host "127.0.0.1" :port 6379}})
(defmacro wcar* [& body] `(car/wcar server1-conn ~@body))

(def alphanumeric "abcdefghijklmnopqrstuvwxyz1234567890")
(defn get-random-id [length]
  (apply str (repeatedly length #(rand-nth alphanumeric))))

(defn create-session [query]
  (let [id (get-random-id 20)]
    (wcar*
      (car/del id)
      (car/set (str "q:" id) query))
    id))

(defn get-session [id]
  (wcar*
    (car/get (str "q:" id))))
