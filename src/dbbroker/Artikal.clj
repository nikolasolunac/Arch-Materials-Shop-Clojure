(ns dbbroker.Artikal
  (:refer-clojure :exclude [update])  
  (:require [dbkonekcija.Konekcija]
            [domen.Artikal :refer :all]
            [domen.TipArtikla :refer :all]
            [util.Util :refer :all]
            [korma.core :refer :all]))


(defentity Artikal)

;get1
(defn vratiArtikle []
  (select Artikal))

;get2
(defn vratiArtikalID 
  [ArtikalID]
    (first (select Artikal (where {:ArtikalID [= ArtikalID]} ) (limit 1))))

;get3
(defn vratiArtikalNaziv 
  [Naziv]
    (first (select Artikal (where {:Naziv [= Naziv]} ) (limit 1))))

;add
(defn dodajArtikal 
  [noviArtikal]
    (let [postojeciArtikal (vratiArtikalNaziv (get noviArtikal :Naziv))])
    (let [postojeciTipArtiklaID (vratiTipArtikla (get noviArtikal :ArtikalID))])
    (if postojeciArtikal 
      "Vec postoji artikal sa tim imenom..."
      (if postojeciTipArtiklaID 
        (if (> (get noviArtikal :Cena) 0) 
          ((let [ubaciArtikal 
            (insert Artikal (values 
              {:Artikal (get noviArtikal :Artikal)
               :Naziv (get noviArtikal :Naziv)
               :Opis (get noviArtikal :Opis)
               :Cena (get noviArtikal :Cena)
               :JedinicaMere (get noviArtikal :JedinicaMere)}))])
          (let [ubaceniArtikal (get ubaciArtikal :generated_key)])
            (vratiArtikalID ubaceniArtikal))
        "Cena mora biti veca od nule...")
      "Ne postoji taj tip artikla...")))

;update
(defn azurirajArtikal 
  [ArtikalID azuriraniArtikal]
    (let [postojeciArtikal (vratiArtikalID ArtikalID)])
    (let [postojeciArtikalNaziv (vratiArtikalNaziv (get azuriraniArtikal :Naziv))])
    (let [postojeciTipArtiklaID (vratiTipArtikla (get azuriraniArtikal :TipArtiklaID))])
    (if postojeciArtikal 
      (if (and postojeciArtikalNaziv (not= (parse-int ArtikalID) (get postojeciArtikalNaziv :ArtikalID))) 
        "Vec postoji artikal sa tim imenom..."
        (if postojeciTipArtiklaID 
          (update Artikal (set-fields 
            {:Artikal (get azuriraniArtikal :Artikal)
             :Naziv (get azuriraniArtikal :Naziv)
             :Opis (get azuriraniArtikal :Opis)
             :Cena (get azuriraniArtikal :Cena)
             :JedinicaMere (get azuriraniArtikal :JedinicaMere)})
          (where {:ArtikalID [= ArtikalID]}))
      "Ne postoji taj tip artikla..."))
    "Ne postoji taj artikal..."))
  
;delete
(defn obrisiArtikal 
  [ArtikalID]
    (let [postojeciArtikal (vratiArtikalID ArtikalID)])
    (if postojeciArtikal 
      (delete Artikal (where {:ArtikalID [= ArtikalID]}))
    "Ne postoji taj artikal..."))