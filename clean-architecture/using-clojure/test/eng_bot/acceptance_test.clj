(ns eng-bot.acceptance-test
  (:require [clojure.test :refer [deftest]]
            [expectations.clojure.test :refer [expecting expect]]
            [eng-bot.test-util.routes :as routes]
            [stub-http.core :as stub-http]
            [clojure.spec.alpha :as s]
            [eng-bot.factory :as factory]
            [eng-bot.channels :as c]))

(s/def ::expected-channels (s/coll-of #(s/valid? ::c/channel %) :min-count 1 :max-count 1))

(deftest acceptance-test
  (expecting "listing channels returns conforming list of channels"
    (stub-http/with-routes!
      routes/slack-channels
      (binding
        [factory/*slack-connection* {:api-url uri :token "test"}]
        (expect ::expected-channels (factory/list-channels)))))

  (expecting "sharing channels posts list of channels to Slack"
    (stub-http/with-routes!
      routes/slack-channels-history-and-post-message
      (binding
        [factory/*slack-connection* {:api-url uri :token "test"}]
        (expect true (factory/share-channels))))))

(factory/share-channels)
