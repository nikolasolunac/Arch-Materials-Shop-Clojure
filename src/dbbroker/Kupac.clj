(ns dbbroker.Kupac
  (:refer-clojure :exclude [update])  
  (:require [dbkonekcija.Konekcija]
            [domen.Kupac :refer :all]
            [util.Util :refer :all]
            [korma.core :refer :all]))


(defentity Kupac)

;get1
(defn vratiKupce []
  (select Kupac))

;get2
(defn vratiKupcaID 
  [KupacID]
    (first (select Kupac (where {:KupacID [= KupacID]} ) (limit 1))))

;get3
(defn vratiKupcaUsername 
  [Username]
    (first (select Kupac (where {:Username [= Username]} ) (limit 1))))

;add
(defn dodajKupca 
  [noviKupac]
    (def postojeciKupac (vratiKupcaUsername (get noviKupac :Username)))  
    (if postojeciKupac 
    "Username zauzet..."
    ((def ubaciKupca (insert Kupac (values 
      {:KupacID (get noviKupac :KupacID)
      :Ime (get noviKupac :Ime)
      :Prezime (get noviKupac :Prezime)
      :Username (get noviKupac :Username)
      :Telefon (get noviKupac :Telefon)
      :Email (get noviKupac :Email)})))
    (def ubacenKupacID (get ubaciKupca :generated_key))
      (vratiKupcaID ubacenKupacID))))

;update
(defn azurirajKupca 
  [KupacID azuriraniKupac]
    (def postojeciKupacID (vratiKupcaID KupacID))
    (def postojeciKupacUsername (vratiKupcaUsername (get azuriraniKupac :Username)))
      (if postojeciKupacID 
        (if (and postojeciKupacUsername (not= (parse-int KupacID) (get postojeciKupacUsername :KupacID))) 
          "Username zauzet..."
          (update Kupac (set-fields 
            {:KupacID (get azuriraniKupac :KupacID)
            :Ime (get azuriraniKupac :Ime)
            :Prezime (get azuriraniKupac :Prezime)
            :Username (get azuriraniKupac :Username)
            :Telefon (get azuriraniKupac :Telefon)
            :Email (get azuriraniKupac :Email)})
          (where {:KupacID [= KupacID]})))
      "Ne postoji taj kupac..."))
  
;delete
(defn obrisiKupca 
  [KupacID]
    (def postojeciKupac (vratiKupcaID KupacID))
    (if postojeciKupac (delete Kupac (where {:KupacID [= KupacID]}))
      "Ne postoji taj kupac..."))