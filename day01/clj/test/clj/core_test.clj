(ns clj.core-test
  (:require [clojure.test :refer :all]
            [clj.core :refer :all]))

(deftest captcha1
  (testing "Part 1 of day01"
    (is (solve-captcha1 "1122") 3)
    (is (solve-captcha1 "1111") 4)
    (is (solve-captcha1 "1234") 0)
    (is (solve-captcha1 "91212129") 9)))

(deftest captcha2
  (testing "Part 2 of day02"
    (is (solve-captcha2 "1212") 6)
    (is (solve-captcha2 "1221") 0)
    (is (solve-captcha2 "123425") 4)
    (is (solve-captcha2 "123123") 12)
    (is (solve-captcha2 "12131415") 4)))
