(ns eng-bot.acceptance-test
  (:require [clojure.test :refer [deftest]]
            [expectations.clojure.test :refer [expect]]
            [eng-bot.test-util.routes :as routes]
            [stub-http.core :as stub-http]
            [clojure.spec.alpha :as s]
            [eng-bot.factory :as factory]
            [eng-bot.github-channel-gateway :as g]))

(s/def ::expected-channels (s/coll-of #(s/valid? ::g/channels %) :min-count 1 :max-count 1))

(deftest list-channels-returns-list-of-channels-with-names
  (stub-http/with-routes!
    routes/slack-channels
    (binding
      [factory/*slack-connection* {:api-url uri :token "test"}]
      (expect ::expected-channels (factory/list-channels)))))

(deftest share-channels-posts-list-of-channels
  (stub-http/with-routes!
    routes/slack-channels-and-post-message
    (binding
      [factory/*slack-connection* {:api-url uri :token "test"}]
      (expect true (factory/share-channels)))))
