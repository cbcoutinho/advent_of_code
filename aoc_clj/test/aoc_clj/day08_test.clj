(ns aoc-clj.day08-test
  (:require [clojure.test :refer :all]
            [aoc-clj.day08 :refer :all]))

(deftest readme-test
  (testing "Examples from the README"
    (is (=
         (update-registers {} "b inc 5 if a > 1")
         {"a" 0 "b" 0}))))
    ;(is (=
         ;(max-register
          ;(aoc-clj.core/file->lines "../day08/sample.txt"))
         ;1))))

(deftest assoc-zero-test
  (testing "Confirm lines are parsed and registers correctly set"
    (let [line "b inc 5 if a > 1"
          test-cases [[{}      {"a" 0 "b" 0}]
                      [{"a" 0} {"a" 0 "b" 0}]
                      [{"a" 2} {"a" 2 "b" 0}]
                      [{"c" 2} {"a" 0 "b" 0 "c" 2}]]]
      (for [[reg-map expected] test-cases]
        (is (=
             (assoc-zero reg-map line)
             expected))))))

(deftest max-register-test
  (testing "Maximum register calculation"
    (let [test-cases [[{"a" 2} 2]
                      [{"a" 0 "b" 1} 1]
                      [{"a" 2 "b" 0} 2]]]
      (for [[input expected] test-cases]
        (is (=
             (max-register input)
             expected))))))
