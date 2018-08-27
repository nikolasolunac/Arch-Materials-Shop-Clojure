(ns dbkonekcija.Konekcija
  (:refer-clojure :exclude [update])  
  (:require [korma.db :as korma]))


;mysql connection
(def dbbrokerKonekcija (korma/mysql 
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "mysql"
   :user "root"
   :password "1111"
   :subname "//localhost:3306/arhmaterijali"}))

;korma connect
(korma/defdb db dbbrokerKonekcija)