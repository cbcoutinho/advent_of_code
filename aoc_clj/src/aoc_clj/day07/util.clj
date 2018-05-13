(ns aoc-clj.day07.util
  (:require [clojure.string :as s]
            [aoc-clj.core :as core]))

(defn get-children
  [coll node]
  (apply :children (filter #(= (:name %) node) coll)))

(defn get-weight
  [coll node]
  (apply :weight (filter #(= (:name %) node) coll)))

(defn get-name
  [coll node]
  (apply :name (filter #(= (:name %) node) coll)))

(defn tokenize-line
  " Turn a line like:
    ngrmq (80) -> cluej, ywrxbgi, saznyj
  into a list like:
    (\"ngrmq\" 80 \"cluej ywrxbgi saznyj\")"
  [line]
  (-> line                ; From input line as string ...
      (s/replace "->" "") ; Replace arrow
      (s/replace "," "")  ; Replace commas
      (s/split #"\s+")))  ; Split on whitespace

(defn create-hash-map
  "Parse collection into hash-map with keys 'node', 'weight', and
  optionally, 'children'"
  ([n w]
   {:name n :weight w})
  ([n w & c]
   {:name n :weight w :children c}))

(defn parse-line
  "Parse line into a hash-map"
  [line]
  (let [coll (tokenize-line line)] ; Tokenize input string into vector
    (apply create-hash-map         ; Create hash-map of parsed input
           (assoc coll             ; Associate collection
                  1                ; at index 1 with ...
                  (-> coll         ; From collection ...
                      second       ; take second string
                      read-string  ; read string -> returns a list
                      first)))))   ; return first/inner value of list

(defn filter-lines
  "Only return lines where :name equals `node`"
  [lines node]
  (->> lines
       (filter #(= (:name %) node))
       first))

;; Create functions for getting set of all keys
(defn get-key
  [k]
  (fn [coll]
    (set (flatten (remove nil?
                          (map #(get % k) coll))))))

(def get-all-children (get-key :children))
(def get-all-names (get-key :name))
