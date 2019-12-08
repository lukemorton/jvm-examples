(ns eng-bot.list-channels-test
  (:require [clojure.test :refer [deftest]]
            [expectations.clojure.test :refer [expect expecting]]
            [eng-bot.list-channels :as lc]))

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

(deftest exec-test
  (expecting "returns empty list from empty gateway"
    (expect empty? (lc/exec fake-empty-gateway)))

  (expecting "returns list from none empty gateway"
    (expect seq (lc/exec fake-gateway)))

  (expecting "returns only engineering channels"
    (let [channel-names (map #(get % :name) (lc/exec fake-gateway))]
      (expect "eng-general" (in channel-names))
      (expect "eng-java" (in channel-names))
      (expect "eng-dotnet" (in channel-names))
      (expect nil? (some #{"announcements"} channel-names))
      (expect nil? (some #{"engagements"} channel-names))
      (expect nil? (some #{"team-sales"} channel-names))))

  (expecting "returns channels sorted by general first and then alphabetical"
    (let [channel-names (mapv #(get % :name) (lc/exec fake-gateway))]
      (expect "eng-general" (get channel-names 0))
      (expect "eng-dotnet" (get channel-names 1))
      (expect "eng-java" (get channel-names 2))
      (expect "eng-ruby" (get channel-names 3)))))
