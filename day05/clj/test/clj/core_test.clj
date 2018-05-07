(ns clj.core-test
  (:require [clojure.test :refer :all]
            [clj.core :refer :all]))

(deftest readme-test
  (testing "Tests example from README."
    (is (= 
          (count-jump-offsets 
            '(0 3 0 1 -3))
          5))))
