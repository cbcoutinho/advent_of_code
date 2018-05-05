(ns clj.core
  (:gen-class))

(defn -main
  "Loop through `args` and convert string of numbers into a list of
  integers"
  [& args]
  (doseq [arg args]
    (println (map #(Character/getNumericValue %) arg))))
