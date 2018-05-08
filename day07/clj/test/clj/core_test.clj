(ns clj.core-test
  (:require [clojure.test :refer :all]
            [clj.core :refer :all]))

(deftest readme-test
  (testing "Test the sample data in the README."
    (is (=
          (find-parent (parse-file "../sample.txt"))
          "tknk"))))

(deftest part1-test
  (testing "Part 1 of day07"
    (is (=
          (find-parent (parse-file "../input.txt"))
          "vvsvez"))))
