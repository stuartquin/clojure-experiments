(ns selected.mysql
  (:import [java.io PushbackReader])
  (:require [clojure.java.jdbc :as j]
            [clojure.java.io :as io]))

(defn load-config [filename]
  (with-open [r (io/reader filename)]
    (read (java.io.PushbackReader. r))))

(let [config (load-config "conf/config.clj")
      db-host (:host (:mysql config))
      db-port 3306
      db-name (:db (:mysql config))]

  (def db {:classname "com.mysql.jdbc.Driver" ; must be in classpath
           :subprotocol "mysql"
           :subname (str "//" db-host ":" db-port "/" db-name)
           :user (:user (:mysql config))
           :password (:password (:mysql config))}))

(defn run-query [query]
  (j/query db
    [query] 
    :as-arrays? true))
