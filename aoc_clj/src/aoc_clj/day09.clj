(ns aoc-clj.day09
  (:require [aoc-clj.core :as core]
            [clojure.string :as s]))

(defn valid?
  [c]
  (case c
    \{ true
    \} true
    false))

(defn iterate-seq
  [input]
  (reduce
   (fn [[acc lvl prev garb?] c]
     ;(println acc lvl prev garb? c)
     (if (= prev \!)
       [acc lvl nil garb?]
       (if (and garb?
                (not= c \>))
         [acc lvl c garb?]
         (if (= c \<)
           [acc lvl c true]
           (case c
             \> [acc lvl c false]
             \{ [acc (inc lvl) c garb?]
             \} [(+ acc lvl) (dec lvl) c garb?]
             [acc lvl c garb?])))))
         ;(if (or ; Check if any the following are true to get out of garbage
            ;garb?  ; Are we in a garbage zone?
            ;(not= prev \!))) ; Are we going to cancel the following letter?
   [0 0 nil false]
   input))

(defn count-groups
  [input]
  (first (iterate-seq input)))

  ;(println input))
