(ns aoc-clj.day07-test
  (:require [clojure.test :refer :all]
            [aoc-clj.day07 :refer :all]))

(deftest readme-test
  (testing "Test the sample data in the README."
    (is (=
         (find-parent (parse-file "../day07/sample.txt"))
         "tknk"))))

(deftest part1-test
  (testing "Part 1 of day07"
    (is (=
         (find-parent (parse-file "../day07/input.txt"))
         "vvsvez"))))
