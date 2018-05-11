(ns aoc-clj.day05
  (:require [clojure.string :as s]
            [aoc-clj.core :as core]))

(defn parse-file
  "Parses file into list of numbers"
  [filename]
  (->> filename
       core/file->lines       ; Read file into vector of lines
       (map #(read-string %)) ; Read string into Clojure (ie. `ints`)
       vec))                  ; Convert to `vec`

;; Use a single function creator for inc-nth and dec-nth
(defn alter-nth
  "Create a function that alters a collection using a function"
  [f]
  (fn [coll idx]
    (assoc coll idx (f (nth coll idx)))))

;; Create inc-nth and dec-nth functions
(def inc-nth (alter-nth inc))
(def dec-nth (alter-nth dec))

(defn out-of-bounds?
  "Check if idx is out of bounds for coll"
  [coll idx]
  (or                       ; Check if index is out of bounds
   (< idx 0)               ; Less than zero
   (>= idx (count coll)))) ; Greater than/equal to length

(defn count-jump-offsets
  "Count number of jump offsets required to exit a collection"
  ([coll] ; Use arity to set initial conditions
   (count-jump-offsets coll 0 0))
  ([coll idx out]
   (if (out-of-bounds? coll idx)
     out
     (recur                   ; Call count-jump-offsets again with `updated` values
      (inc-nth coll idx)     ; Increment the coll at index idx
      (+ idx (nth coll idx)) ; Jump with offset at index
      (inc out)))))          ; Increment number of jumps

(defn count-jump-offsets2
  "Count number of jump offsets required to exit a collection

  Part 2 - decrease by one if offset is 3 or more instead of
  incrementing"
  ([coll]
   (count-jump-offsets2 coll 0 0))
  ([coll idx out]
   (if (out-of-bounds? coll idx)
     out
     (recur
      (if (>= (nth coll idx) 3)
        (dec-nth coll idx)
        (inc-nth coll idx))
      (+ idx (nth coll idx))
      (inc out)))))
