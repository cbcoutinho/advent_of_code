(ns aoc-clj.day09-test
  (:require [clojure.test :refer :all]
            [aoc-clj.day09 :refer :all]))

(deftest readme-test
  (testing "Examples from README"
    ;(is (=
         ;(count-groups (seq "{}"))
         ;1))
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
             expected))))))
