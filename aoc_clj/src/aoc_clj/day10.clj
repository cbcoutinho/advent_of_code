(ns aoc-clj.day10)

(defn my-subvec
  "Create a subvec that wraps around the original vector"
  [v start end]
  (map
   #(nth v (mod % (count v)))
   (range start end)))

(defn reverse-partition
  "Reverse the elements of a subset of a vector"
  [rng pos len]
  (reduce
   (fn [acc [idx n]]
     (assoc acc idx n))            ; Associate the idx-th element with the number n
   rng                             ; Start with original vector
   (map
    vector                         ; Intertwine the two following vectors
    (->> (range pos (+ pos len))   ; Create a simple index vector
         (map #(mod % (count rng)))) ; And change the index to wrap around
    (-> rng                        ; Take the original vector
        (my-subvec                 ; Take a subvector of it
         pos
         (+ pos len))
        reverse))))                ; And reverse the elements

(defn next-knot
  "Calcuate the next 'knot' in the sequence"
  [rng pos len]
  (reverse-partition rng pos len))

(defn knot-hash
  "Calculates the cryptographic knot-hash"
  [size lengths]
  (let [[knot _ _] (reduce
                    (fn [[acc pos skip] length]
                      [(reverse-partition acc pos length)
                       (-> (+ pos length skip)
                           (mod size))
                       (inc skip)])
                    [(into [] (range size)) 0 0]
                    lengths)]
    (* (first knot) (second knot))))
