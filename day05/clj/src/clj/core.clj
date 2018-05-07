(ns clj.core
  (:gen-class)
  (:require [clojure.string :as s]))


(defn parse-file
  "Parses file into list of numbers"
  [filename]
  (as-> filename f
    (slurp f)                 ; Slurp file contents into memory
    (s/split-lines f)         ; Split input at line breaks
    (map #(read-string %) f)  ; Read string into Clojure (ie. `ints`)
    (vec f)))                 ; Convert to `vec`

(defn inc-nth
  "Increment the nth element in a collection"
  [coll idx]
  (assoc coll idx
         (inc (nth coll idx))))

(defn count-jump-offsets
  "Count number of jump offsets required to exit a collection"
  [coll]
  (with-local-vars
    [idx 0 out 0]
    (println @idx @out)))



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
