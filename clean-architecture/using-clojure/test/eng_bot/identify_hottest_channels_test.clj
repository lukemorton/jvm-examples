(ns eng-bot.identify-hottest-channels-test
  (:require [clojure.test :refer [deftest]]
            [expectations.clojure.test :refer [expecting expect]]
            [clojure.spec.alpha :as s]
            [spy.core :refer [spy called? called-once?]]
            [eng-bot.identify-hottest-channels :as identify-hottest-channels]
            [eng-bot.channels :as c]))

(defn- fake-list-channels
  []
  [{::c/id "C0001" ::c/name "eng-general"}
   {::c/id "C0002" ::c/name "eng-java"}
   {::c/id "C0003" ::c/name "eng-dotnet"}
   {::c/id "C0004" ::c/name "eng-ruby"}
   {::c/id "C0005" ::c/name "eng-test"}])

(defn- fake-channel-messages-since
  [channel-name since]
  (case channel-name
    "C0001" (vec (replicate 55 {::c/ts "1358546515.000007"
                                ::c/user "U2147483896"
                                ::c/text "Nice one!"}))
    "C0002" (vec (replicate 10 {::c/ts "1358546515.000007"
                                ::c/user "U2147483896"
                                ::c/text "Nice one!"}))
    "C0003" (vec (replicate 45 {::c/ts "1358546515.000007"
                                ::c/user "U2147483896"
                                ::c/text "Nice one!"}))
    "C0004" (vec (replicate 7 {::c/ts "1358546515.000007"
                               ::c/user "U2147483896"
                               ::c/text "Nice one!"}))
    "C0005" (vec (replicate 100 {::c/ts "1358546515.000007"
                                 ::c/user "U2147483896"
                                 ::c/text "Nice one!"}))))


(s/def ::expected-hot-channels (s/coll-of #(s/valid? ::c/hot-channel %) :min-count 3 :max-count 3))

(deftest exec-test
  (expecting "returns hot-channels that conform to spec"
    (expect ::expected-hot-channels (identify-hottest-channels/exec fake-list-channels fake-channel-messages-since)))
  (expecting "returns hot-channels sorted by recent message counts excluding general and test"
    (let [hot-channels (identify-hottest-channels/exec fake-list-channels fake-channel-messages-since)]
      (expect 45 (get (get hot-channels 0) ::c/recent-message-count))
      (expect 10 (get (get hot-channels 1) ::c/recent-message-count))
      (expect 7 (get (get hot-channels 2) ::c/recent-message-count))))
  (expecting "calls list-channels"
    (let [fake-list-channels-spy (spy fake-list-channels)]
      (identify-hottest-channels/exec fake-list-channels-spy fake-channel-messages-since)
      (expect called-once? fake-list-channels-spy)))

  (expecting "calls list-channels"
    (let [fake-channel-messages-since-spy (spy fake-channel-messages-since)]
      (identify-hottest-channels/exec fake-list-channels fake-channel-messages-since-spy)
      (expect called? fake-channel-messages-since-spy))))




