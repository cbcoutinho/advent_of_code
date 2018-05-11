(ns aoc-clj.day06
  (:require [aoc-clj.core :as core]))

(defn max-idx
  "Calculates the index of the max element in a collection"
  [coll]
  (->> coll
       (map-indexed vector)         ; Convert coll into a index/value vector pair
       (filter #(=                  ; Filter based on ...
                 (second %)         ; whether the second value in the index/value pair ...
                 (apply max coll))) ; equals the maximum value in the original collection
       first                        ; Take the first resulting index/value pair
       first))                      ; Return the index

(def inc-nth (core/alter-nth inc))

(defn redistribute
  "Redistribute the blocks in a memory bank to the other banks of the
  area by removing the blocks of the bank with the most blocks.

  Start with zeroing out the block with the most memory, then move
  one-by-one incrementing each block until we run out of memory."
  [coll]
  (with-local-vars [my-coll (assoc coll (max-idx coll) 0)
                    start (inc (max-idx coll))
                    end (+ (inc (max-idx coll)) (nth coll (max-idx coll)))]
    (doseq [n (range @start @end)]
      (var-set my-coll (inc-nth @my-coll (mod n (count coll)))))
    @my-coll))

(defn first-duplicate
  "Finds the first duplicate pair of an infinite sequence. Uses
  reduced to stop consuming sequence once duplicate is found.

  https://stackoverflow.com/a/19895886/5536001"
  [coll]
  (reduce (fn [acc [idx x]]
            (if-let [v (get acc x)]
              (reduced (conj v idx))
              (assoc acc x [idx])))
          {} (map-indexed #(vector % %2) coll)))

(defn count-cycles
  "Calculates how many cycles it takes to get to a previously seen
  state - looks for duplicates"
  [coll]
  (->> coll                   ; Using original collection
       (iterate redistribute) ; Continually redistribute input
       first-duplicate        ; Return indexes of first duplicate
       second))               ; Return second index

(defn loop-size
  "Calculates the number of block redistribution cycles required
  between cycle loops"
  [coll]
  (let [start-end (->> coll                   ; Using original collection
                       (iterate redistribute) ; Continually redistribute input ...
                       first-duplicate)]      ; Return first duplicate
    (- (apply max start-end) (apply min start-end))))
