(ns domen.Artikal
  (:refer-clojure :exclude [update])  
  (:require [schema.core :as s]
            [ring.swagger.schema :refer [coerce!]]))


(s/defschema Artikal 
  {:ArtikalID Integer
   :Naziv String
   :Opis String
   :Cena Double
   :JedinicaMere String})


(s/defschema NoviArtikal (dissoc Artikal :ArtikalID))