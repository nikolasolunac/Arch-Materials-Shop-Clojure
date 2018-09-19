(ns domen.TipArtikla
  (:refer-clojure :exclude [update])  
  (:require [schema.core :as s]
            [ring.swagger.schema :refer [coerce!]]))


(s/defschema TipArtikla 
  {:TipArtiklaID Integer
   :Naziv String})


(s/defschema NoviTipArtikla (dissoc TipArtikla :TipArtiklaID))