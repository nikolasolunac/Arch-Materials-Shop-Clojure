(ns dbbroker.TipArtikla
  (:refer-clojure :exclude [update]) 
  (:require [dbkonekcija.Konekcija]
            [domen.TipArtikla :refer :all]
            [util.Util :refer :all]
            [korma.core :refer :all]))


(defentity TipArtikla)

;get1
(defn vratiTipoveArtikla []
  (select TipArtikla))

;get2
(defn vratiTipArtiklaID 
  [TipArtiklaID]
    (first (select TipArtikla (where {:TipArtiklaID [= TipArtiklaID]} ) (limit 1))))

;get3
(defn vratiTipArtiklaNaziv 
  [Naziv]
    (first (select TipArtikla (where {:Naziv [= Naziv]} ) (limit 1))))

;add
(defn dodajTipArtikla 
  [noviTipArtikla]
  (let [postojeciTipArtiklaNaziv (vratiTipArtiklaNaziv (get noviTipArtikla :Naziv))])
  (if postojeciTipArtiklaNaziv 
    "Vec postoji tip artikla sa tim imenom..."
  ((let [ubaciTipArtikla (insert TipArtikla (values
    {:TipArtiklaID (get noviTipArtikla :TipArtiklaID)
     :Naziv (get noviTipArtikla :Naziv)}))])
  (let [ubaceniTipArtikla (get ubaciTipArtikla :generated_key)])
      (vratiTipArtiklaID ubaceniTipArtikla))))

;update
(defn azurirajTipArtikla 
  [TipArtiklaID azuriraniTipArtikla]
  (let [postojeciTipArtiklaID (vratiTipArtiklaID TipArtiklaID)])
  (let [postojeciTipArtiklaNaziv (vratiTipArtiklaNaziv (get azuriraniTipArtikla :Naziv))])
  (if postojeciTipArtiklaID 
    (if (and postojeciTipArtiklaNaziv (not= (parse-int TipArtiklaID) (get postojeciTipArtiklaNaziv :TipArtiklaID))) 
      "Vec postoji tip artikla sa tim imenom..."
      (update TipArtikla
        (set-fields {:TipArtiklaID (get azuriraniTipArtikla :TipArtiklaID)
                     :Naziv (get azuriraniTipArtikla :Naziv)})
        (where {:TipArtiklaID [= TipArtiklaID]})))
  "Ne postoji taj tip artikla..."))
  
;delete
(defn obrisiTipArtikla 
  [TipArtiklaID]
  (let [postojeciTipArtiklaID (vratiTipArtiklaID TipArtiklaID)])
  (if postojeciTipArtiklaID 
    (delete TipArtiklaID (where {:TipArtiklaID [= TipArtiklaID]}))
      "Ne postoji taj tip artikla..."))