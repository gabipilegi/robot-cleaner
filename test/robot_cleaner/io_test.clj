(ns robot-cleaner.io-test
  (:require [robot-cleaner.io :as io]
            [clojure.test :refer :all]))

(deftest input-list
  (testing "reads input with no commands"
    (is (= (with-in-str "0\n1 2\n" (io/input-list!))
           ["1 2"])))
  (testing "reads input with one command"
    (is (= (with-in-str "1\n1 2\nE 1\n" (io/input-list!))
           ["1 2" "E 1"])))
  (testing "reads input with various commands"
    (is (= (with-in-str "4\n1 2\nW 1\nE 2\nS 5\nN 4\n" (io/input-list!))
           ["1 2" "W 1" "E 2" "S 5" "N 4"]))))
