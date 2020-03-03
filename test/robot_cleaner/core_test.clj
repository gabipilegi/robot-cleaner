(ns robot-cleaner.core-test
  (:require [robot-cleaner.core :as core]
            [clojure.test :refer :all]))

(deftest run-cleaner!
  (testing "With input"
    (is (= "=> Cleaned: 4\n"
           (with-out-str (with-in-str "2\n10 22\nE 2\nN 1" (core/run-cleaner!)))))))
