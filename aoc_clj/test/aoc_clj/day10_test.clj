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

  (testing "Example for part2 from README"
    (is (=
         (parse-input "1,2,3")
         [49 44 50 44 51 17 31 73 47 23]))
    (is (=
         (knot-hash2 "")
         "a2582a3a0e66e6e86e3812dcb672a272"))
    (is (=
         (knot-hash2 "AoC 2017")
         "33efeb34ea91902bb2f59c9920caa6cd"))
    (is (=
         (knot-hash2 "1,2,3")
         "3efbe78a8d82f29979031a4aa0b16a9d"))
    (is (=
         (knot-hash2 "1,2,4")
         "63960835bcdc130f0b66d7ff4f6a5a8e")))

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
