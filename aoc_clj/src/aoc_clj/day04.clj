(ns aoc-clj.day04
  (:require [clojure.string :as s]
            [aoc-clj.core :as core]))

(defn parse-file
  [filename]
  (map
   #(s/split % #" ")
   (core/file->lines filename)))

(defn valid-passphrase
  "Checks if a passphrase is valid - returns true if collection
  contains only distinct values"
  [coll]
  (apply distinct? coll))

(defn valid-passphrase2
  "Checks if a passphrase is valid - returns true if collection
  contains only distinct values _including anagrams_. This means that 
  each string is sorted to compare combinations, not just permutations"
  [coll]
  (apply distinct? (map sort coll)))

(defn count-valid
  "Count valid passphrases in a collection of passphrases"
  [filename]
  (->> filename
       parse-file
       (filter valid-passphrase)
       count))

(defn count-valid2
  "Same as count-valid, but using valid-passphrase2"
  [filename]
  (->> filename
       parse-file
       (filter valid-passphrase2)
       count))
