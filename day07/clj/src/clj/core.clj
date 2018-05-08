(ns clj.core
  (:gen-class)
  (:require [clojure.string :as s]
            [clojure.set :refer [difference]]))


(defn tokenize-line
  [line]
  (-> line                ; From input line as string ...
      (s/replace "->" "") ; Replace arrow
      (s/replace "," "")  ; Replace commas
      (s/split #"\s+")))  ; Split on whitespace

(defn create-hash-map
  "Parse collection into hash-map with keys 'node', 'weight', and
  optionally, 'children'"
  ([n w]
   ;(println n w))
   {:name n :weight w})
  ([n w & c]
   {:name n :weight w :children c}))

(defn parse-line
  "Parse line into a collection of hash-maps"
  [line]
  (let [coll (tokenize-line line)] ; Tokenize input string into vector
    (apply create-hash-map         ; Create hash-map of parsed input
      (assoc coll                  ; Associate collection
        1                          ; at index 1 ...
        (-> coll                   ; From collection ...
            second                 ; take second string
            read-string            ; read string
            first)))))             ; return first value

(defn parse-file
  "Parse file into a collection of hash-maps"
  [filename]
  (map parse-line
    (-> filename
        slurp
        s/split-lines)))

;; Create functions for getting set of all keys
(defn get-key
  [k]
  (fn [coll]
    (set (flatten (remove nil?
                          (map #(get % k) coll))))))

(def get-children (get-key :children))
(def get-names (get-key :name))

(defn find-parent
  [tree]
  (let [names (get-names tree)
        children (get-children tree)]
    (first (difference names children))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
