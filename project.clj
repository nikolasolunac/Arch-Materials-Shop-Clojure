(defproject materials-shop-clj "0.1.0-SNAPSHOT"
  :description "materials-shop-clj"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                [clj-http "3.9.1"]
                [ring/ring-core "1.7.0-RC2"]
                [ring/ring-jetty-adapter "1.7.0-RC2"],
                [ring/ring-json "0.4.0"]
                [ring/ring-defaults "0.3.2"]
                [korma "0.4.3"]
                [mysql/mysql-connector-java "5.1.6"]
                [metosin/compojure-api "2.0.0-alpha23"]]
:ring {:handler kontroler.Kontroler/materialsShop}
:plugins [[lein-ring "0.12.4"]
[compojure "1.6.1"]]
:target-path "target/%s"
:profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "4.0.1"]]}}
)