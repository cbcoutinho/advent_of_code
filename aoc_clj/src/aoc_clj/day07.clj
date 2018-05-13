(ns aoc-clj.day07
  (:require [clojure.string :as s]
            [clojure.set :refer [difference]]
            [aoc-clj.core :as core]
            [aoc-clj.day07.util :as util]))

(defn parse-file
  "Parse file into a collection of hash-maps"
  [filename]
  (->> filename
       core/file->lines
       (map util/parse-line)))

(defn find-parent
  [tree]
  (let [names (util/get-all-names tree)
        children (util/get-all-children tree)]
    (first (difference names children))))

(defn build-tree
  [lines]
  (for [child (-> lines
                  (util/filter-lines (find-parent lines))
                  :children)]
    (util/filter-lines lines child)))

(defn build-tree-full
  [lines]
  (let [line (-> lines
                 (util/filter-lines
                  (find-parent lines)))]
    (assoc-in line                  ; Associate the nested key in `line` map
              [:children]           ; at the children key
              (replace              ; And replace the current value with ...
               (zipmap              ; Zip the following two lists into a map ...
                (:children line)    ; A list of the children of the line map
                (build-tree lines)) ; Return the `line` maps of the children of the parent
               (:children line))))) ; Replace the values within this list
