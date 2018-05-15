(ns aoc-clj.day09
  (:require [aoc-clj.core :as core]
            [clojure.string :as s]))

(defn iterate-chars
  [input]
  (reduce
   (fn [[acc                                    ; Accumulated total
         lvl                                    ; Current brace level
         prev                                   ; Previous character - or nil if was precided by \!
         garb?                                  ; Boolean - if in garbage patch or not
         acc-garb]
        c]                                      ; Next char in sequence

     (if (= prev \!)                            ; If prev char is !
       [acc lvl nil garb? acc-garb]                      ; Set new prev to nil and continue

       (if (and garb?                           ; If both in garbage zone ...
                (not= c \>))                    ; And not the end of garbage zone ...
         (if (not= c \!)
           [acc lvl c garb? (inc acc-garb)]                      ; Just continue
           [acc lvl c garb? acc-garb])                      ; Just continue

         (if (= c \<)                           ; If start garbage zone ...
           [acc lvl c true acc-garb]                     ; Set garb? to true

           (case c                              ; Otherwise use a catch block
             \> [acc lvl c false acc-garb]               ; End garbage
             \{ [acc (inc lvl) c garb? acc-garb]         ; Start new level
             \} [(+ acc lvl) (dec lvl) c garb? acc-garb] ; End level
             [acc lvl c garb? acc-garb])))))             ; Assume just basic character
   [0 0 nil false 0]
   input))

(defn count-groups
  [input]
  (first ; Return only accumulated total
   (iterate-chars input)))

(defn count-garbage
  [input]
  (last
   (iterate-chars input)))
