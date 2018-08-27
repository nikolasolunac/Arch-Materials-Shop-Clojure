(ns util.Util)

(defn parse-int [s]
  (Integer/parseInt (re-find #"\A-?\d+" s)))