(ns aoc-clj.day08-test
  (:require [clojure.test :refer :all]
            [aoc-clj.day08 :refer :all]))

(deftest readme-test
  (testing "Examples from the README - essentially doing each step one-by-one"
    (is (=
         (update-registers {} "b inc 5 if a > 1")
         {"a" 0 "b" 0}))
    (is (=
         (update-registers {"a" 0 "b" 0} "a inc 1 if b < 5")
         {"a" 1 "b" 0}))
    (is (=
         (update-registers {"a" 1 "b" 0} "c dec -10 if a >= 1")
         {"a" 1 "b" 0 "c" 10}))
    (is (=
         (update-registers {"a" 1 "b" 0 "c" 10} "c inc -20 if c == 10")
         {"a" 1 "b" 0 "c" -10}))

    (is (=
         (-> "../day08/sample.txt"
             parse-file
             max-register)
         1))
    (is (=
         (-> "../day08/input.txt"
             parse-file
             max-register)
         4647))

    (is (=
         (-> "../day08/input.txt"
             parse-file-max-each
             second)
         5590))))

(deftest assoc-zero-test
  (testing "Confirm lines are parsed and registers correctly set"
    (let [line "b inc 5 if a > 1"
          test-cases [[{}      {"a" 0 "b" 0}]
                      [{"a" 0} {"a" 0 "b" 0}]
                      [{"a" 2} {"a" 2 "b" 0}]
                      [{"c" 2} {"a" 0 "b" 0 "c" 2}]]]
      (doseq [[reg-map expected] test-cases]
        (is (=
             (assoc-zero reg-map line)
             expected))))))

(deftest max-register-test
  (testing "Maximum register calculation"
    (let [test-cases [[{"a" 2} 2]
                      [{"a" 0 "b" 1} 1]
                      [{"a" 2 "b" 0} 2]]]
      (doseq [[input expected] test-cases]
        (is (=
             (max-register input)
             expected))))))
