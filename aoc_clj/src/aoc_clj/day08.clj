(ns aoc-clj.day08
  (:require [aoc-clj.core :as core]
            [clojure.string :as s]))

(defn cond->vec
  "Converts the string conditional into a vector, and then
  swaps the first and second elements"
  [c]
  (-> c
      (s/split #" ") ; Split conditional string on white space
      ((fn [c]       ; Associate the last string as an integer
         (assoc
          c
          2 (Integer/parseInt (last c)))))
      ((fn [c]       ; Swap the first and second elements of the vector
         (assoc
          c
          1 (first c)
          0 (second c))))))

(defn parse-line
  "Parses a single line instruction into a vector of strings, each
  representing a register, instruction, and conditional

  Make sure to allow +/- signs in regex, drawing from this answer
  for inspiration:
    https://stackoverflow.com/a/30619026/5536001
  And these docs:
    https://clojure.org/reference/other_functions#regex"
  [line]
  (->> line        ; Start with line
       (re-matches ; Match groups on first word, operation, number, and condition
        #"(\w+)\s+(inc|dec)\s+([+-]?[0-9]+)\s+if\s+(.*)")
       rest))      ; Return everything besides the first element (original string)

(defn check-cond
  "Checks string conditional against known registers"
  [registers c]
  (let [v (cond->vec c)
        op (case (-> v first)
             ">" > ">=" >=
             "<" < "<=" <=
             "==" = "!=" not=)]
    (op
     (get registers (second v))
     (last v))))

(defn assoc-zero
  "This function associates missing keys in a map with zero
  doesn't exist - otherwise just return the original map"
  [registers line]
  (let [[reg1 op n c] (parse-line line)
        [_ reg2 _] (cond->vec c)]
    (reduce
     (fn [acc k]    ; Associate all missing keys with zeros
       (if-not (contains? acc k)
         (assoc acc k 0)
         acc))
     registers      ; Starting from the original map
     [reg1 reg2]))) ; Loop over the two input registers in instruction

(defn max-register
  "Calculates the maximum register value"
  [registers]
  (reduce                       ; Loop through map and hold onto max value
   (fn [acc [k v]]
     (max acc v))
   (let [start (-> registers first second)]
     (if (nil? start)
       0       ; Start max at zero if empty
       start)) ; Start max at value of first key entry
   (rest registers)))          ; Send the rest of the key/val pairs through reduce

(defn apply-update
  "Updates register map based on operation and conditional in line"
  [registers line]
  (let [[reg op n c] (parse-line line)
        op (case op                  ; Converts the string operation to clojure function
             "inc" +
             "dec" -)]
    (if (check-cond registers c)     ; Evaluates conditional
      (assoc registers reg           ; Associates register value ...
             (op                     ; by using the operator
              (get registers reg)    ; on the current register value
              (Integer/parseInt n))) ; and the value in the instruction
      registers)))

(defn update-registers
  "Applies instruction to register hashmap"
  [registers line]
  (-> registers
      (assoc-zero line)
      (apply-update line)))

(defn parse-file
  "Parse input file of register instructions"
  [filename]
  (->> filename
       core/file->lines  ; Convert file into a vector of strings
       (reduce
        update-registers ; Loop through the register map and update them one-by-one
        {})))            ; Start with an empty register map

(defn parse-file-max-each
  "Essentially the same as max-register, except calculates it on each iteration"
  [filename]
  (->> filename
       core/file->lines
       (reduce
        (fn [[acc max-reg] line]
          (vector
           (update-registers acc line)
           (max (max-register acc) max-reg)))
        [{} 0])))
