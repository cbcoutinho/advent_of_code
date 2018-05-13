(ns aoc-clj.day08-test
  (:require [clojure.test :refer :all]
            [aoc-clj.day08 :refer :all]))

(deftest readme-test
  (testing "Examples from the README"
    (is (=
         (update-registers {} "b inc 5 if a > 1")
         {"a" 0 "b" 0}))
    (is (=
         (max-register
           ;(parse-file "../day08/sample.txt")
          (aoc-clj.core/file->lines "../day08/sample.txt"))
         1))))
