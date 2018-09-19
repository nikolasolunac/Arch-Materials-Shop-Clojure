(ns domen.Kupac
  (:refer-clojure :exclude [update]) 
  (:require [schema.core :as s]
            [ring.swagger.schema :refer [coerce!]]))


(s/defschema Kupac 
  {:KupacID Integer
   :Ime String
   :Prezime String
   :Username String
   :Telefon String
   :Email String})


(s/defschema NoviKupac (dissoc Kupac :KupacID))