(ns clj.core
  (:gen-class))

(defn sqr
  "Calcuates square of a number"
  [n]
  (* n n))

(def corners
  (map sqr (iterate (partial + 2) 1)))

