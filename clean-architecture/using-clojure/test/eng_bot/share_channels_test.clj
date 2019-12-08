(ns eng-bot.share-channels-test
  (:require [clojure.test :refer [deftest]]
            [expectations.clojure.test :refer [expect expecting]]
            [clojure.spec.alpha :as s]
            [spy.core :refer [spy called-once? calls]]
            [eng-bot.share-channels :as sc]))

(defn- fake-empty-list-channels
  []
  [])

(defn- fake-list-channels
  []
  [{:name "eng-general"}
   {:name "eng-java"}
   {:name "eng-dotnet"}
   {:name "eng-ruby"}])

(defn- fake-post-message
  [channel text]
  true)

(s/def ::post-message-call (s/cat :channel string? :text string?))

(deftest exec-test
  (expecting "returns true on success"
    (expect true (sc/exec fake-list-channels fake-post-message)))

  (expecting "returns true even with empty channels"
    (expect true (sc/exec fake-empty-list-channels fake-post-message)))

  (expecting "calls list-channels"
    (let [fake-list-channels-spy (spy fake-list-channels)]
      (sc/exec fake-list-channels-spy fake-post-message)
      (expect called-once? fake-list-channels-spy)))

  (expecting "calls post-message"
    (let [fake-post-message-spy (spy fake-post-message)]
      (sc/exec fake-list-channels fake-post-message-spy)
      (expect called-once? fake-post-message-spy)
      (expect ::post-message-call (first (calls fake-post-message-spy))))))