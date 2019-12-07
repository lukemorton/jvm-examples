(ns eng-bot.share-channels-test
  (:require [eng-bot.share-channels :as sc]
            [clojure.spec.alpha :as s]
            [spy.core :refer [spy called-once? calls]]
            [eng-bot.test-util.spec :refer [is-conforming-to-spec]]))

(use 'clojure.test)

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

(deftest exec-returns-true-on-success
  (is (= true (sc/exec fake-list-channels fake-post-message))))

(deftest exec-returns-true-even-with-empty-channels
  (is (= true (sc/exec fake-empty-list-channels fake-post-message))))

(deftest exec-calls-list-channels
  (let [fake-list-channels-spy (spy fake-list-channels)]
    (sc/exec fake-list-channels-spy fake-post-message)
    (is (called-once? fake-list-channels-spy))))

(s/def ::post-message-call (s/cat :channel string? :text string?))

(deftest exec-calls-post-message
  (let [fake-post-message-spy (spy fake-post-message)]
    (sc/exec fake-list-channels fake-post-message-spy)
    (is (called-once? fake-post-message-spy))
    (is-conforming-to-spec ::post-message-call (first (calls fake-post-message-spy)))))
