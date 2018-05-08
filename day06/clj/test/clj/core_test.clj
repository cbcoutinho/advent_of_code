(ns clj.core-test
  (:require [clojure.test :refer :all]
            [clj.core :refer :all]))

(deftest readme-test
  (testing "Test readme example."
    (is (=
          (count-cycles [0 2 7 0])
          5))))

(deftest part1-test
  (testing "Part 1"
    (is (=
          (count-cycles [2 8 8 5 4 2 3 1 5 5 1 2 15 13 5 14])
          3156))))
