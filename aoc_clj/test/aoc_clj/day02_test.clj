(ns aoc-clj.day02-test
  (:require [aoc-clj.day02 :refer :all]
            [clojure.test :refer :all]))

(deftest readme-test
  (testing "Sample input from README"
    (is (= (solve-checksum (parse-file "../day02/sample_short.txt"))
           18))))

(deftest solve-checksum2-test
  (testing "Sample input 2 from README"
    (is (=
         (solve-checksum2 (parse-file "../day02/sample_short2.txt"))
         9))
    (is (=
         (solve-checksum2 (parse-file "../day02/sample_long2.txt"))
         351))))
