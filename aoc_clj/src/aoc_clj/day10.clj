(ns aoc-clj.day10
  (:require [aoc-clj.day10.util :as util]
            [clojure.string :as s]))

(defn parse-input
  "Converts a string into a collection of integer lengths. Characters
  are converted to their integer ASCII codes.

  \"1,2,3\" -> [49 44 50 44 51 17 31 73 47 23]"
  [string]
  (-> string              ; Start with string
      util/string-to-ints ; Convert string to a vector of integers
      util/append-vec))   ; Append the default vector

(defn sparse-to-dense
  "Converts a 'sparse' hash to a 'dense' hash by partitioning each
  collection in groups of 16 integers and numerically bit XOR-ing them

  A single partition of 16 elements results in a single numeric output:

  [65 27 9 1 4 3 40 50 91 7 6 0 2 5 68 22] -> (64)"
  [coll]
  (->> coll
       (partition 16)         ; Partition the collection into groups of 16
       (map                   ; Map each the following function onto each partition
        (fn [[x & others]]    ; Destructure into first and rest
          (reduce bit-xor
                  x           ; Begin with first
                  others))))) ; Consecutively apply bit-xor to each element

(defn next-knot
  "Calculate the next 'knot' in the sequence by reversing the
  elements in a subset of the vector"
  [rng pos len]
  (reduce
   (fn [acc [idx n]]
     (assoc acc idx n))              ; Associate the idx-th element with the number n
   rng                               ; Start with original vector
   (map
    vector                           ; Intertwine the two following vectors
    (->> (range pos (+ pos len))     ; Create a simple index vector
         (map #(mod % (count rng)))) ; And change the index to wrap around
    (-> rng                          ; Take the original vector
        (util/my-subvec              ; Take a subvector of it
         pos
         (+ pos len))
        reverse))))                  ; And reverse the elements

(defn knot-iter
  "Performs a single iteration of the knot-hash algorithm

  In short, it calls the `next-knot` function to !suprise! calculate
  the next knot in the sequence, and takes care of the skip and pos
  counters"
  [[acc pos skip] length]
  (vector                     ; Construct a vector from arguments
   (next-knot acc pos length) ; Calculate next knot in sequence
   (-> (+ pos length skip)    ; Determine next register position
       (mod (count acc)))     ; Make sure pos stays within range
   (inc skip)))               ; Increment skip

(defn calc-knot
  "Calculates the first iteration of the knot-hash function. This
  essentially starts postion and skip counters at zero."
  [size lengths]
  (reduce
   knot-iter
   [(vec (range size)) 0 0]
   lengths))

(defn calc-knot-wrap
  "Wraps the calc-knot function so it can be iterated"
  [size lengths]
  (->> (calc-knot size lengths)
       (iterate (fn [[acc pos skip]]
                  (reduce
                   knot-iter
                   [acc pos skip]
                   lengths)))))

(defn knot-hash1
  "Calculates the cryptographic knot-hash for part 1"
  [size lengths]
  (-> (calc-knot size lengths) ; Calculate knot from input
      first                    ; Take just the resulting knot
      ((fn [[x y & _]]         ; Destructure vector
         (* x y)))))           ; Multiply first two elements

(defn knot-hash2
  "Calculates the cryptographic knot-hash for part 2"
  [size lengths]
  (-> (calc-knot-wrap size lengths)
      (nth 63)
      first
      sparse-to-dense
      ((fn [coll]
         (apply str (map util/num2hx coll))))))
