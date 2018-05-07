(ns clj.core-test
  (:require [clojure.test :refer :all]
            [clj.core :refer :all]))

(deftest readme-test
  (testing "Checks passphrase validity for passphrases from the README"
    (is (true?
          (valid-passphrase '("aa" "bb" "cc" "dd" "ee"))))
    (is (false?
          (valid-passphrase '("aa" "bb" "cc" "dd" "aa"))))
    (is (true?
          (valid-passphrase '("aa" "bb" "cc" "dd" "aaa"))))))

(deftest part1-test
  (testing "Part 1 of day 4"
    (is (=
          (count-valid "../passphrases.txt")
          477))
    (is (=
          (count-valid2 "../passphrases.txt")
          167))))
