(ns clj.core-test
  (:require [clojure.test :refer :all]
            [clj.core :refer :all]))

(deftest readme-test 
  (testing "Sample input from README"
    (is (= (solve-checksum 
             (parse-file "../sample_short.txt")) 
           18))))

(deftest solve-checksum2-test
  (testing "Sample input 2 from README"
    (is (= (solve-checksum2
             (parse-file "../sample_short2.txt"))
           9))
    (is (= (solve-checksum2
             (parse-file "../sample_long2.txt"))
           351))))
              
