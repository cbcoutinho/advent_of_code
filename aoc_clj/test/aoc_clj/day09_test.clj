(ns aoc-clj.day09-test
  (:require [clojure.test :refer :all]
            [aoc-clj.day09 :refer :all]))

(deftest readme-test
  (testing "Examples from README"
    (let [test-cases [["{}" 1]
                      ["{{{}}}" 6]
                      ["{{},{}}" 5]
                      ["{{{},{},{{}}}}" 16]
                      ["{<a>,<a>,<a>,<a>}" 1]
                      ["{{<ab>},{<ab>},{<ab>},{<ab>}}" 9]
                      ["{{<!!>},{<!!>},{<!!>},{<!!>}}" 9]
                      ["{{<a!>},{<a!>},{<a!>},{<ab>}}" 3]]]
      (for [[input expected] test-cases]
        (is (=
             (count-groups input)
             expected))))))
