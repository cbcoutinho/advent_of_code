(ns aoc-clj.day04
  (:require [clojure.string :as s]))

(defn parse-file1
  "Uses threading first macro `->`, which assumes input to each
  function is first arguement after function symbol - (fn arg1 ...).
  This forces `map` outside of threading macro because map needs
  its arguement (actually a collection) at the end - (map fn arg1)"
  [filename]
  (map #(s/split % #" ")
       (-> filename
           slurp
           s/split-lines)))

(defn parse-file2
  "Use clever as-> threading macro instead of -> to more explicitly
  place arguments either at front or back of form"
  [filename]
  (as-> filename f
    (slurp f)
    (s/split-lines f)
    (map #(s/split % #" ") f)))

(defn valid-passphrase
  "Checks if a passphrase is valid - returns true if collection
  contains only distinct values"
  [coll]
  (apply distinct? coll))

(defn valid-passphrase2
  "Checks if a passphrase is valid - returns true if collection
  contains only distinct values _including anagrams_."
  [coll]
  (apply distinct? (map sort coll)))

(defn count-valid
  "Count valid passphrases in a collection of passphrases.

  Optionally allow to use a different parse-file function - by default
  it uses the parse-file1 function"
  ([filename] (count-valid filename parse-file1))
  ([filename parse-fn]
   (count (filter
           valid-passphrase
           (parse-fn filename)))))

(defn count-valid2
  "Same as count-valid, but using valid-passphrase2"
  [filename]
  (count (filter
          valid-passphrase2
          (parse-file1 filename))))
