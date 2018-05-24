(ns aoc-clj.day10
  (:require [aoc-clj.day10.util :as util]))

(defn my-subvec
  "Create a subvec that wraps around the original vector"
  [v start end]
  (map
   #(nth v (mod % (count v)))
   (range start end)))

(defn reverse-partition
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
        (my-subvec                 ; Take a subvector of it
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
