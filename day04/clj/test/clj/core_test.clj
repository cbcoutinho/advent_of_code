(ns clj.core-test
  (:require [clojure.test :refer :all]
            [clj.core :refer :all]))

(deftest readme-test
  (testing "Checks passphrase validity for passphrases from the README"
    (is (= 
          (valid-passphrase '("aa" "bb" "cc" "dd" "ee"))
          true))
    (is (=
          (valid-passphrase '("aa" "bb" "cc" "dd" "aa"))
          false))
    (is (=
          (valid-passphrase '("aa" "bb" "cc" "dd" "aaa"))
          true))))
