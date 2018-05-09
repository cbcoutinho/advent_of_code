(ns aoc-clj.day04-test
  (:require [clojure.test :refer :all]
            [aoc-clj.day04 :refer :all]))

(deftest readme-test
  (testing "Checks passphrase validity for passphrases from the README"
    (is (valid-passphrase '("aa" "bb" "cc" "dd" "ee")))
    (is (not (valid-passphrase '("aa" "bb" "cc" "dd" "aa"))))
    (is (valid-passphrase '("aa" "bb" "cc" "dd" "aaa")))))

(deftest part1-test
  (testing "Part 1 of day 4"
    (is (=
         (count-valid "../day04/passphrases.txt")
         477))
    (is (=
         (count-valid2 "../day04/passphrases.txt")
         167))))
