(ns aoc-clj.day10.util)

(defn num2hx
  "Converts a number (base 10) to a hexadecimal (base 16)"
  [n]
  (Integer/toString n 16))
