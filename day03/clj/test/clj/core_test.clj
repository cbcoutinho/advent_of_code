(ns clj.core-test
  (:require [clojure.test :refer :all]
            [clj.core :refer :all]))

(deftest carry-data-test
  (testing "Example data from README"
    (is (= (num-steps-carry-data 1)
           0))
    (is (= (num-steps-carry-data 12)
           3))
    (is (= (num-steps-carry-data 23)
           23))
    (is (= (num-steps-carry-data 1024)
           31))))
