(ns aoc-clj.day02
  (:require [aoc-clj.core :as core]))

(defn parse-file
  "Parse a file containing lines of integers into a list of lists"
  [filename]
  (map #(read-string (str "(" % ")"))
       (core/file->lines filename)))

(defn sum-max-min
  [my-list]
  (- (apply max my-list) (apply min my-list)))

(defn solve-checksum
  "Solves checksum problem of part 1 day 2"
  [list-of-lists]
  (reduce + (map sum-max-min list-of-lists)))

(defn is-whole-divisor?
  "Boolean if `numer` is a whole divisor of `denom`. Also makes sure
  that `numer` and `denom` are not identical"
  [numer denom]
  (and
   (not= numer denom)
   (= 0 (mod numer denom))))

(defn whole-divisor
  "Determine whole number that results from dividing two numbers in
  the input list"
  [my-list]
  (reduce list
          (for [n my-list]
            (map #(/ % n)
                 (filter #(is-whole-divisor? % n) my-list)))))

(defn solve-checksum2
  "Solves checksum problem part 2 of day 2 - for each line calculate
  the whole number that results of dividing one number by another.
  There should only be one per line."
  [list-of-lists]
  (reduce +
          (flatten (map whole-divisor list-of-lists))))
