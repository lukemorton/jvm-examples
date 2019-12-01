(ns eng-bot.list-channels-test
  (:require [eng-bot.list-channels :as lc]))

(use 'clojure.test)

(defn- fake-empty-gateway
  []
  [])

(defn- fake-gateway
  []
  [{:name "announcements"}
   {:name "eng-general"}
   {:name "eng-java"}
   {:name "eng-dotnet"}
   {:name "eng-ruby"}])

(deftest exec-returns-empty-list-from-empty-gateway
  (is (empty? (lc/exec fake-empty-gateway))))

(deftest exec-returns-list-from-none-empty-gateway
  (is (not (empty? (lc/exec fake-gateway)))))

(deftest exec-returns-list-of-channels-with-names
  (let [channels (lc/exec fake-gateway)]
    (is (= "eng-general" (get (get channels 0) :name)))
    (is (= "eng-java" (get (get channels 1) :name)))
    (is (= "eng-dotnet" (get (get channels 2) :name)))
    (is (= "eng-ruby" (get (get channels 3) :name)))))
