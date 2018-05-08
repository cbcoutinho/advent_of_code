(ns clj.core-test
  (:require [clojure.test :refer :all]
            [clj.core :refer :all]))

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
            (parse-file "../offsets.txt"))
          376976))))
