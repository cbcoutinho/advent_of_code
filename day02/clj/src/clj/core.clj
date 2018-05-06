(ns clj.core
  (:gen-class))


(defn parse-file
  "Parse a file containing lines of integers into a list of lists"
  [filename]
  (let [vec-lines (-> filename
                      slurp
                      clojure.string/split-lines)]
    (map #(read-string (str "(" % ")")) 
         vec-lines)))

(defn sum-max-min
  [my-list]
  (- (apply max my-list) (apply min my-list))) 

(defn solve-checksum
  "Solves checksum problem of part 1 day 2"
  [list-of-lists]
  (reduce + (map sum-max-min list-of-lists)))

(defn -main
  "Main function - input filename"
  [filename & args]
  (println
    (solve-checksum (parse-file filename))))
