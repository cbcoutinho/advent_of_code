(ns aoc-clj.day01-test
  (:require [clojure.test :refer :all]
            [aoc-clj.day01 :refer :all]))

(deftest captcha1
  (testing "Part 1 of day01"
    (let [test-cases [["1122" 3]
                      ["1111" 4]
                      ["1234" 0]
                      ["91212129" 9]]]
      (doseq [[value expected] test-cases]
        (is (solve-captcha1 value) expected)))))

(deftest captcha2
  (testing "Part 2 of day02"
    (let [test-cases [["1212" 6]
                      ["1221" 0]
                      ["123425" 4]
                      ["123123" 12]
                      ["12131415" 4]]]
      (doseq [[value expected] test-cases]
        (is (solve-captcha2 value) expected)))))
