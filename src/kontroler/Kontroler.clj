(ns kontroler.Kontroler
  (:refer-clojure :exclude [update])  
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [domen.Artikal :refer :all]
            [domen.Kupac :refer :all]
            [domen.TipArtikla :refer :all]   
            [dbbroker.Artikal :refer :all]
            [dbbroker.Kupac :refer :all]
            [dbbroker.TipArtikla :refer :all]   
  )
)

; Swagger
(def materialsShop
  (api 
    {
      :swagger {
        :ui "/"
        :spec "/swagger.json"
        :data {
          :info { :title "Arch Materials Shop Clj"}
          :tags [
            {:name "Kupci"}
            {:name "Artikli"}
            {:name "Tipovi artikala"}
          ]
        }
      }
    }


    ; Artikal CRUD
    (context "artikli" []
      :tags ["/artikli"]

      (GET "/" []
        :return [Artikal]
        :summary "<Vrati sve artikle>"
        (ok (vratiArtikle)))

      (GET "/:id" []
        :path-params [id :- s/Any]
        :summary "<Vrati artikal po ID-u>"
        (def artikalBaza (vratiArtikalID id))
        (if artikalBaza (ok artikalBaza) (not-found))
      )

      (POST "/" []
        :summary "<Kreiraj novi artikal"
        :body [noviArtikal noviArtikal]
        (def kreiranArtikalR (dodajArtikal noviArtikal))
        (if (= (type kreiranArtikalR) java.lang.String) 
          (bad-request kreiranArtikalR)
          (ok kreiranArtikalR) 
        )
      )

      (PUT "/:id" []
        :summary "<Azuriraj artikal>"
        :path-params [id :- s/Any]
        :body [azuriraniArtikal noviArtikal]
        (def azuriraniArtikalR (azurirajArtikal id azuriraniArtikal))
        (if (= (type azuriraniArtikalR) java.lang.Integer) 
          (ok nil) 
          (bad-request azuriraniArtikalR)
        )
      )

      (DELETE "/:id" []
        :summary "<Obrisi artikal>"
        :path-params [id :- s/Any]
        (def obrisiArtikalR (obrisiArtikal id))
        (if (= (type obrisiArtikalR) java.lang.String) 
          (bad-request obrisiArtikalR)
          (ok nil) 
        )
      )
    )


    ; Kupac CRUD
    (context "kupci" []
      :tags ["kupci"]

      (GET "/" []
        :return [Kupac]
        :summary "<Vrati sve kupce>"
        (ok (vratiKupce)))

      (GET "/:id" []
        :path-params [id :- s/Any]
        :summary "<Vrati kupca po ID-u>"
        (def kupacBaza (vratiKupcaID id))
        (if kupacBaza (ok kupacBaza) (not-found))
      )

      (POST "/" []
        :summary "<Kreiraj novog kupca>"
        :body [noviKupac noviKupac]
        (def kreiraniKupacR (dodajKupca noviKupac))
        (if (= (type kreiraniKupac) java.lang.String) 
          (bad-request kreiraniKupac)
          (ok kreiraniKupac) 
        )
      )

      (PUT "/:id" []
        :summary "<Azuriraj kupca>"
        :path-params [id :- s/Any]
        :body [azuriraniKupac noviKupac]
        (def azuriraniKupacR (azurirajKupca id azuriraniKupac))
        (if (= (type azuriraniKupacR) java.lang.Integer) 
          (ok nil) 
          (bad-request azuriraniKupacR)
        )
      )

      (DELETE "/:id" []
        :summary "<Obrisi kupca>"
        :path-params [id :- s/Any]
        (def obrisiKupcaR (obrisiKupca id))
        (if (= (type obrisiKupcaR) java.lang.String) 
          (bad-request obrisiKupcaR)
          (ok nil) 
        )
      )
    )

    
    ; Tip Artikla CRUD
    (context "tipoviartikala" []
      :tags ["tipoviartikala"]

      (GET "/" []
        :return [TipArtikla]
        :summary "<Vrati sve tipove artikala>"
        (ok (vratiTipoveArtikla)))

      (GET "/:id" []
        :path-params [id :- s/Any]
        :summary "<Vrati tip artikla po ID-u>"
        (def tipArtiklaBaza (vratiTipArtiklaID id))
        (if tipArtiklaBaza (ok tipArtiklaBaza) (not-found))
      )

      (POST "/" []
        :summary "<Kreiraj novi tip artikla>"
        :body [noviTipArtikla noviTipArtikla]
        (def kreiraniTipArtiklaR (dodajTipArtikla noviTipArtikla))
        (if (= (type kreiraniTipArtiklaR) java.lang.String) 
          (bad-request kreiraniTipArtiklaR)
          (ok kreiraniTipArtiklaR) 
        )
      )

      (PUT "/:id" []
        :summary "<Azuriraj tip artikla>"
        :path-params [id :- s/Any]
        :body [azuriraniTipArtikla noviTipArtikla]
        (def azuriraniTipArtiklaR (azurirajTipArtikla id azuriraniTipArtikla))
        (if (= (type azuriraniTipArtiklaR) java.lang.Integer) 
          (ok nil) 
          (bad-request azuriraniTipArtiklaR)
        )
      )

      (DELETE "/:id" []
        :summary "<Obrisi tip artikla>"
        :path-params [id :- s/Any]
        (def obrisiTipArtiklaR (obrisiTipArtikla id))
        (if (= (type obrisiTipArtiklaR) java.lang.String) 
          (bad-request obrisiTipArtiklaR)
          (ok nil) 
        )
      )
    )
  )
)