(ns clj.core
  (:gen-class))

(defn string-to-list-ints 
  "Convert a string of ints into a list of ints"
  [my-string]
  (map #(Character/getNumericValue %) my-string))

(defn compare-next [list-ints idx]
  (= (nth list-ints idx)
     (nth list-ints (mod (inc idx) (count list-ints)))))

(defn compare-around [list-ints idx]
  (= (nth list-ints idx)
     (nth list-ints 
          (mod (+ idx (/ 2 (count list-ints))) 
               (count list-ints)))))

(defn solve-captcha1 
  "Computes the captcha for part 1 of day01"
  [my-string]
  (def list-ints (string-to-list-ints my-string))
  (def ^:dynamic out)
  (binding [out 0]
    (dorun
      (for [idx (range (count list-ints))]
        (if (compare-next list-ints idx)
          (set! out (+ out (nth list-ints idx))))))
    out))
 
(defn solve-captcha2
  "Computes the captcha for part 2 of day01"
  [my-string]
  (def list-ints
    (string-to-list-ints my-string))
  (def ^:dynamic out)
  (binding [out 0]
    (dorun
      (for [idx (range (count list-ints))]
        (if (compare-around list-ints idx)
          (set! out (+ out (nth list-ints idx))))))
    out))
  
