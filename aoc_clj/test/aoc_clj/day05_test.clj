(ns aoc-clj.day05-test
  (:require [aoc-clj.day05 :refer :all]
            [clojure.test :refer :all]))

(deftest readme-test
  (testing "Tests example from README."
    (is (=
          (count-jump-offsets [0 3 0 1 -3])
          5))
    (is (=
          (count-jump-offsets2 [0 3 0 1 -3])
          10))))

(deftest offsets-test
  (testing "Test offsets.txt file"
    (is (=
          (count-jump-offsets
            (parse-file "../day05/offsets.txt"))
          376976))
    (is (=
          (count-jump-offsets2
            (parse-file "../day05/offsets.txt"))
          29227751))))
