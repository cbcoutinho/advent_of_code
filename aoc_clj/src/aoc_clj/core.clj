(ns aoc-clj.core
  (:require [clojure.string :as s]))

(defn file->lines
  "Parse a file into a vector of strings"
  [filename]
  (-> filename
      slurp
      s/split-lines))

(defn alter-nth
  "Alter the nth value in a collection using f"
  [f]
  (fn [coll idx]
    (assoc coll idx
           (f (nth coll idx)))))

(defmacro infix
  "Converts infix notation (1 + 1) to Polish notation (+ 1 1)"
  [form]
  (list (second form)
        (first form)
        (last form)))
