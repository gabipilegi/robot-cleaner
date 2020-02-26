(ns robot-cleaner.io-test
  (:require [clojure.test :refer :all]
            [robot-cleaner.io :as io]))

(deftest read-all
  (testing "Inputs with no commands"
    (is (= {:start-position {:x 1 :y 2}}
           (with-in-str "0\n1 2\n" (io/read-all!)))))
  (testing "Inputs with one command"
    (is (=  {:start-position {:x 3 :y 4}
             :commands       [{:compass-direction :E
                               :number-of-steps   1}]}
            (with-in-str "1\n3 4\nE 1\n" (io/read-all!)))))

  (testing "Inputs with various commands"
    (is (= {:start-position {:x 4 :y 6}
            :commands       [{:compass-direction :W
                              :number-of-steps   1}
                             {:compass-direction :E
                              :number-of-steps   2}
                             {:compass-direction :S
                              :number-of-steps   5}
                             {:compass-direction :N
                              :number-of-steps   4}]}
           (with-in-str "4\n4 6\nW 1\nE 2\nS 5\nN 4\n" (io/read-all!)))))

  (testing "Inputs with maximum possible values"
    (is (= {:start-position {:x 100 :y 100}
            :commands       [{:compass-direction :E
                              :number-of-steps   100}]}
           (with-in-str "1\n100 100\nE 100\n" (io/read-all!)))))

  (testing "Inputs with minimum possible values"
    (is (= {:start-position {:x -100 :y -100}
            :commands       [{:compass-direction :E
                              :number-of-steps   -100}]}
           (with-in-str "1\n-100 -100\nE -100\n" (io/read-all!))))))
