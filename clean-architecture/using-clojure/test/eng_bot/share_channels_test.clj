(ns eng-bot.share-channels-test
  (:require [clojure.test :refer [deftest]]
            [expectations.clojure.test :refer [expecting expect]]
            [clojure.spec.alpha :as s]
            [spy.core :refer [spy called-once? calls]]
            [eng-bot.share-channels :as share-channels]))

(defn- fake-empty-list-channels
  []
  [])

(defn- fake-list-channels
  []
  [{:name "eng-general"}
   {:name "eng-java"}
   {:name "eng-dotnet"}
   {:name "eng-ruby"}])

(defn- fake-identify-hottest-channels
  []
  [{:name "eng-java" :recent-message-count 44}
   {:name "eng-dotnet" :recent-message-count 35}
   {:name "eng-ruby" :recent-message-count 12}])

(defn- fake-post-message
  [text]
  true)

(s/def ::post-message-call (s/cat :text string?))

(deftest exec-test
  (expecting "returns true on success"
    (expect true (share-channels/exec fake-list-channels fake-identify-hottest-channels fake-post-message)))

  (expecting "returns true even with empty channels"
    (expect true (share-channels/exec fake-empty-list-channels fake-identify-hottest-channels fake-post-message)))

  (expecting "calls list-channels"
    (let [fake-list-channels-spy (spy fake-list-channels)]
      (share-channels/exec fake-list-channels-spy fake-identify-hottest-channels fake-post-message)
      (expect called-once? fake-list-channels-spy)))

  (expecting "calls identify-hottest-channels"
    (let [fake-identify-hottest-channels-spy (spy fake-identify-hottest-channels)]
      (share-channels/exec fake-list-channels fake-identify-hottest-channels-spy fake-post-message)
      (expect called-once? fake-identify-hottest-channels-spy)))

  (expecting "calls post-message"
    (let [fake-post-message-spy (spy fake-post-message)]
      (share-channels/exec fake-list-channels fake-identify-hottest-channels fake-post-message-spy)
      (expect called-once? fake-post-message-spy)
      (expect ::post-message-call (first (calls fake-post-message-spy))))))