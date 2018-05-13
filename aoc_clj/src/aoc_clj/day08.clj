(ns aoc-clj.day08
  (:require [aoc-clj.core :as core]
            [clojure.string :as s]))

(defn parse-line
  "Associates a single line instruction with an operation on the
  register hashmap object"
  [line]
  (->> line        ; Start with line
       (re-matches ; Match groups on first word, operation, number, and condition
        #"([a-zA-Z]*)\s+(inc|dec)\s+(\d+)\s+if\s+(.*)")
       rest))      ; Return everything besides the first element

(defn myfun
  [registers line]
  (let [k (-> line
              parse-line
              last
              (clojure.string/split #" ")
              first)]
    (if (nil? (get registers k))
      (assoc registers k 0)
      registers)))

(defn update-registers
  "Applies register instruction to registers hashmap"
  [registers line]
  (if (empty? registers)
    {"a" 0 "b" 0}))

(defn lines->regmaps
  "Converts lines to hashmap of registers (keys) and operations (values)"
  [lines]
  (reduce
   (fn [acc [idx & x]]
     (let [v (get acc idx)]
       (do
         (println idx)
         (assoc acc idx v))))
   {}
   (map #(parse-line %) lines)))

(defn parse-file
  "Parse input file of register instructions"
  [filename]
  (->> filename
       core/file->lines
       lines->regmaps))

(defn max-register
  "Calculates the maximum register value"
  [registers]
  1)
