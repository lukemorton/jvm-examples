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
   {:name "eng-ruby"}
   {:name "engagements"}
   {:name "team-sales"}])

(deftest exec-returns-empty-list-from-empty-gateway
  (is (empty? (lc/exec fake-empty-gateway))))

(deftest exec-returns-list-from-none-empty-gateway
  (is (not (empty? (lc/exec fake-gateway)))))

(deftest exec-only-returns-eng-channels
  (let [channel-names (map #(get % :name) (lc/exec fake-gateway))]
    (is (some #{"eng-general"} channel-names))
    (is (some #{"eng-java"} channel-names))
    (is (some #{"eng-dotnet"} channel-names))
    (is (not (some #{"announcements"} channel-names)))
    (is (not (some #{"engagements"} channel-names)))
    (is (not (some #{"team-sales"} channel-names)))))

(deftest exec-sorts-channel-names-with-general-first-and-then-alphabetical
  (let [channel-names (mapv #(get % :name) (lc/exec fake-gateway))]
    (is (= "eng-general" (get channel-names 0)))
    (is (= "eng-dotnet" (get channel-names 1)))
    (is (= "eng-java" (get channel-names 2)))
    (is (= "eng-ruby" (get channel-names 3)))))
