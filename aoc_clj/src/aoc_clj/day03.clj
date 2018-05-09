(ns aoc-clj.day03)

(defn sqr
  "Calcuates square of a number"
  [n]
  (* n n))

(def corners
  (map sqr (iterate (partial + 2) 1)))
