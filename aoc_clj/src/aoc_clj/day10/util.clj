(ns aoc-clj.day10.util)

(defn num2hx
  "Converts a number (base 10) to a hexadecimal (base 16)"
  [n]
  (Integer/toString n 16))

(defn my-subvec
  "Create a subvec that wraps around the original vector"
  [v start end]
  (map
   #(nth v (mod % (count v)))
   (range start end)))
