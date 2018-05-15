(ns aoc-clj.day10-test
  (:require [clojure.test :refer :all]
            [aoc-clj.day10 :refer :all]
            [clojure.string :as s]))

(deftest readme-test
  (testing "Individual steps from README"
    (is (=
         (next-knot [0 1 2 3 4] 0 3)
         [2 1 0 3 4]))
    (is (=
         (next-knot [2 1 0 3 4] 3 4)
         [4 3 0 1 2]))
    (is (=
         (next-knot [4 3 0 1 2] 3 1)
         [4 3 0 1 2]))
    (is (=
         (next-knot [4 3 0 1 2] 1 5)
         [3 4 2 1 0])))

  (testing "Example from the README"
    (is (=
         (knot-hash 5 [3 4 1 5])
         12)))

  (testing "Input data part 1"
    (is (=
         (knot-hash 256 (-> "../day10/input.txt"
                            slurp
                            s/split-lines
                            first
                            (s/split #",")
                            ((fn [coll] (map #(Integer/parseInt %) coll)))
                            vec))
         1935))))
