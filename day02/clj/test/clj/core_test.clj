(ns clj.core-test
  (:require [clojure.test :refer :all]
            [clj.core :refer :all]))

(deftest readme-test 
  (testing "Sample input from README"
    (is (= (solve-checksum 
             (parse-file "../sample_short.txt")) 
           18))))
