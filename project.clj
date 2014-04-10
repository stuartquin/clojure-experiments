(defproject selected "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.8.10"]]
  :main selected.core
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.taoensso/carmine "2.6.0"]
                 [org.clojure/java.jdbc "0.3.3"]
                 [mysql/mysql-connector-java "5.1.6"]
                 [http-kit "2.1.16"]
                 [cheshire "5.1.1"]
                 [ring/ring-devel "1.1.8"]
                 [ring/ring-core "1.2.2"]
                 [ring/ring-json "0.1.2"]
                 [compojure "1.1.5"]
                 [ring/ring-jetty-adapter "1.2.2"]]
  :profiles {:dev {:dependencies [[midje "1.5.1"]]}})
