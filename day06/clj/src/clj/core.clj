(ns clj.core
  (:gen-class))

(defn max-idx
  "Calculates the index of the max element in a collection"
  [coll]
  ;(first (apply max-key second (map-indexed vector blocks)))
  (first
    (first
      (filter
        #(=
           (second %)
           (apply max coll))
        (map-indexed vector coll)))))

(defn alter-nth
  [f]
  (fn [coll idx]
    (assoc coll idx
           (f (nth coll idx)))))

(def inc-nth (alter-nth inc))

(defn redistribute
  "Redistribute the blocks in a memory bank to the other banks of the
  area by removing the blocks of the bank with the most blocks"
  [coll]
  (with-local-vars
    [my-coll (assoc coll (max-idx coll) 0)
     start (inc (max-idx coll))
     end (+ (inc (max-idx coll)) (nth coll (max-idx coll)))]

    (doseq [n (range @start @end)]
      (var-set my-coll (inc-nth @my-coll (mod n (count coll)))))
    @my-coll))

(defn first-duplicate [coll]
  "Finds the first duplicate pair of an infinite sequence. Uses
  reduced to stop consuming sequence once duplicate is found.

  https://stackoverflow.com/a/19895886/5536001"
  (reduce (fn [acc [idx x]]
            (if-let [v (get acc x)]
              (reduced (conj v idx))
              (assoc acc x [idx])))
          {} (map-indexed #(vector % %2) coll)))

(defn count-cycles
  "Calcuates how many cycles it takes to get to a previously seen
  state - looks for duplicates"
  [coll]
  (second (first-duplicate (iterate redistribute coll))))

(defn loop-size
  "Calcuates the number of block redistribution cycles required between
  cycle loops"
  [coll]
  (let 
    [start-end (first-duplicate (iterate redistribute blocks))]
    (- (apply max start-end) (apply min start-end))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
