(ns eng-bot.share-channels-test
  (:require [eng-bot.share-channels :as sc]
            [spy.core :refer [spy called-with?]]))

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
  [& {:keys [channel text]}]
  true)

(deftest exec-returns-true-on-success
  (is (= true (sc/exec fake-list-channels fake-post-message))))

(deftest exec-returns-true-even-with-empty-channels
  (is (= true (sc/exec fake-empty-list-channels fake-post-message))))

(deftest exec-calls-list-channels
  (let [fake-list-channels-spy (spy fake-list-channels)]
    (sc/exec fake-list-channels-spy fake-post-message)
    (is (called-with? fake-list-channels-spy))))

(deftest exec-calls-post-message
  (let [fake-post-message-spy (spy fake-post-message)]
    (sc/exec fake-empty-list-channels fake-post-message-spy)
    (is (called-with? fake-post-message-spy :channel "eng-test" :text []))))
