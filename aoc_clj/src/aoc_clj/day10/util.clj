(ns aoc-clj.day10.util)

(defn num2hx
  "Converts a number (base 10) to a hexadecimal (base 16) string.
  Optionally pads the string with a zero if only a single letter, due
  to being less than 16

   1 -> \"01\"
  15 -> \"0f\"
  16 -> \"10\""
  [n]
  (cond->> (Integer/toString n 16)
    (< n 16) (str "0")))

(defn my-subvec
  "Create a subvec that wraps around the original vector"
  [v start end]
  (map
   #(nth v (mod % (count v)))
   (range start end)))

(defn string-to-ints
  "Converts a string to a vector of integers. Characters are converted
  to their respective ASCII codes

  \"1,2,3\" -> [49 44 50 44 51]"
  [string]
  (->> string      ; Start with the input string
       (map int)   ; Convert each character to an integer
       (into []))) ; Mold results into a vector

(defn append-vec
  "Appends a vector of integers to a collection.
  Part 2 of day 10 requires that we append a random vector of elements
  to the input collection.

  [1 2 3] -> [1 2 3 17 31 73 47 23]"
  ([coll] ; If no vector is supplied, use the default
   (append-vec coll [17 31 73 47 23]))
  ([coll app-vec]
   (reduce     ; Use reduce to append the vector element-by-element
    conj       ; Use conj to append to the input vector
    coll       ; Input collection
    app-vec))) ; Append each element of this vector
