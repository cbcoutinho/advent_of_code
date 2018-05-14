(ns aoc-clj.day08
  (:require [aoc-clj.core :as core]
            [clojure.string :as s]))

(defn parse-line
  "Associates a single line instruction with an operation on the
  register hashmap object"
  [line]
  (->> line        ; Start with line
       (re-matches ; Match groups on first word, operation, number, and condition
        #"(\w+)\s+(inc|dec)\s+(\d+)\s+if\s+(.*)")
       rest))      ; Return everything besides the first element (original string)

(defn assoc-zero
  "This function associates missing keys in a map with zero
  doesn't exist - otherwise just return the original map"
  [registers line]
  (let [[reg op n c] (parse-line line)]
    (reduce
     (fn [acc k] ; Associate all missing keys with zeros
       (if-not (contains? acc k)
         (assoc acc k 0)
         acc))
     registers   ; Starting from the original map
     [reg        ; First register in string
      (-> c      ; Register contained in the conditional
          (s/split #" ")
          first)])))

(defn update-registers
  "Applies register instruction to registers hashmap"
  [registers line]
  (-> registers
      (assoc-zero line)))

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
  (reduce                       ; Loop through map and hold onto max value
   (fn [acc [k v]]
     (max acc v))
   (-> registers first second) ; Start max at value of first key entry
   (rest registers)))          ; Send the rest of the key/val pairs through reduce
