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
      (doseq [[input expected] test-cases]
        (is (=
             (count-groups (seq input))
             expected)))))

  (testing "Examples from part 2 of README"
    (let [test-cases [["<>" 0]
                      ["<random characters>" 17]
                      ["<<<<>" 3]
                      ["<{!>}>" 2]
                      ["<!!>" 0]
                      ["<!!!>>" 0]
                      ["<{o\"i!a,<{i<a>" 10]]]
      (doseq [[input expected] test-cases]
        (is (=
             (count-garbage (seq input))
             expected))))))
