(ns aoc-clj.day07
  (:require [clojure.string :as s]
            [clojure.set :refer [difference]]
            [aoc-clj.core :as core]))

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

(defn parse-file
  "Parse file into a collection of hash-maps"
  [filename]
  (->> filename
       core/file->lines
       (map parse-line)))

(defn filter-lines
  [lines node]
  (->> lines
       (filter #(= (:name %) node))
       first))

(defn build-tree
  [lines]
  (for [child (-> lines
                  (filter-lines (find-parent lines))
                  :children)]
    ;(replace)
    (filter-lines lines child)))

(defn build-tree-full
  [lines]
  (let [line (-> lines
                 (filter-lines
                  (find-parent lines)))]
    (assoc-in line                  ; Associate the nested key in `line` map
              [:children]           ; at the children key
              (replace              ; And replace the current value with ...
               (zipmap              ; Zip the following two lists into a map ...
                (:children line)    ; A list of the children of the line map
                (build-tree lines)) ; Return the `line` maps of the children of the parent
               (:children line))))) ; Replace the values within this list

;; Create functions for getting set of all keys
(defn get-key
  [k]
  (fn [coll]
    (set (flatten (remove nil?
                          (map #(get % k) coll))))))

(def get-all-children (get-key :children))
(def get-all-names (get-key :name))

(defn find-parent
  [tree]
  (let [names (get-all-names tree)
        children (get-all-children tree)]
    (first (difference names children))))

(defn get-children
  [coll node]
  (apply :children (filter #(= (:name %) node) coll)))

(defn get-weight
  [coll node]
  (apply :weight (filter #(= (:name %) node) coll)))

(defn get-name
  [coll node]
  (apply :name (filter #(= (:name %) node) coll)))
