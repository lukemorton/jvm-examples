(ns eng-bot.list-channels-test
  (:require [clojure.test :refer [deftest]]
            [expectations.clojure.test :refer [expecting expect]]
            [eng-bot.list-channels :as list-channels]
            [eng-bot.channels :as c]))

(defn- fake-empty-gateway
  []
  [])

(defn- fake-gateway
  []
  [{::c/name "announcements"}
   {::c/name "eng-general"}
   {::c/name "eng-java"}
   {::c/name "eng-dotnet"}
   {::c/name "eng-ruby"}
   {::c/name "engagements"}
   {::c/name "team-sales"}])

(deftest exec-test
  (expecting "returns empty list from empty gateway"
    (expect empty? (list-channels/exec fake-empty-gateway)))

  (expecting "returns list from none empty gateway"
    (expect seq (list-channels/exec fake-gateway)))

  (expecting "returns only engineering channels"
    (let [channel-names (map #(get % ::c/name) (list-channels/exec fake-gateway))]
      (expect "eng-general" (in channel-names))
      (expect "eng-java" (in channel-names))
      (expect "eng-dotnet" (in channel-names))
      (expect nil? (some #{"announcements"} channel-names))
      (expect nil? (some #{"engagements"} channel-names))
      (expect nil? (some #{"team-sales"} channel-names))))

  (expecting "returns channels sorted by general first and then alphabetical"
    (let [channel-names (mapv #(get % ::c/name) (list-channels/exec fake-gateway))]
      (expect "eng-general" (get channel-names 0))
      (expect "eng-dotnet" (get channel-names 1))
      (expect "eng-java" (get channel-names 2))
      (expect "eng-ruby" (get channel-names 3)))))
