(ns aoc-clj.day10
  (:require [aoc-clj.day10.util :as util]
            [clojure.string :as s]))

(defn parse-input
  "Converts a string into a collection of integer lengths. Characters
  are converted to their integer ASCII codes"
  [string]
  (reduce             ; Use reduce to append the vector element-by-element
   conj               ; Use conj to append to the input vector
   (->> string        ; Start with the input string
        (map int)     ; Convert each character to an integer
        (into []))    ; Mold results into a vector
   [17 31 73 47 23])) ; Append each element of this vector


(defn next-knot
  "Calcuate the next 'knot' in the sequence by reversing the
  elements in a subset of the vector"
  [rng pos len]
  (reduce
   (fn [acc [idx n]]
     (assoc acc idx n))            ; Associate the idx-th element with the number n
   rng                             ; Start with original vector
   (map
    vector                         ; Intertwine the two following vectors
    (->> (range pos (+ pos len))   ; Create a simple index vector
         (map #(mod % (count rng)))) ; And change the index to wrap around
    (-> rng                        ; Take the original vector
        (util/my-subvec                 ; Take a subvector of it
         pos
         (+ pos len))
        reverse))))                ; And reverse the elements

(defn calc-knot
  [size lengths]
  (reduce
   (fn [[acc pos skip] length]
     [(next-knot acc pos length)
      (-> (+ pos length skip)
          (mod size))
      (inc skip)])
   [(into [] (range size)) 0 0]
   lengths))

(defn knot-hash
  "Calculates the cryptographic knot-hash"
  [size lengths]
  (let [knot (first
              (calc-knot size lengths))]
    (*
     (first knot)
     (second knot))))

(defn knot-hash2
  "Calculates the cryptographic knot-hash for part 2"
  [input])
