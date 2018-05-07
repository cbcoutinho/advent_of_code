(ns clj.core
  (:gen-class)
  (:require [clojure.string :as s]))


(defn parse-file
  "Parses file into list of numbers"
  [filename]
  (as-> filename f
    (slurp f)
    (s/split-lines f)
    (map #(read-string %) f)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
