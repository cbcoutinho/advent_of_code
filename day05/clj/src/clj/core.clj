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

(defn dec-nth
  "Decrement the nth element in a collection"
  [coll idx]
  (assoc coll idx
         (dec (nth coll idx))))

(defn count-jump-offsets
  "Count number of jump offsets required to exit a collection"
  ([coll] ; Use arity to set initial conditions
   (count-jump-offsets coll 0 0))
  ([coll idx out]
   (if
     (or ; Check if index falls outside of range
       (< idx 0) ; less than zero
       (>= idx (count coll))) ; greater than/equal to length
     out
     (recur ; Call count-jump-offsets again with `updated` values
       (inc-nth coll idx) ; Increment the coll at index idx
       (+ idx (nth coll idx)) ; Jump with offset at index
       (inc out))))) ; Increment number of jumps

(defn count-jump-offsets2
  "Count number of jump offsets required to exit a collection

  Part 2 - decrease by one if offset is 3 or more instead of
  incrementing"
  ([coll]
   (count-jump-offsets2 coll 0 0))
  ([coll idx out]
   (if
     (or
       (< idx 0)
       (>= idx (count coll)))
     out
     (recur
       (if (>= (nth coll idx) 3)
         (dec-nth coll idx)
         (inc-nth coll idx))
       (+ idx (nth coll idx))
       (inc out)))))


(defn -main
  "I don't do a whole lot ... yet."
  [filename & args]
  (println
    (count-jump-offsets
    ;(count-jump-offsets2
      (parse-file filename))))
