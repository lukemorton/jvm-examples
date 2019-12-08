(ns eng-bot.github-channel-gateway-test
  (:require [clojure.test :refer [deftest]]
            [expectations.clojure.test :refer [expecting expect]]
            [clojure.spec.alpha :as s]
            [stub-http.core :as stub-http]
            [eng-bot.test-util.routes :as routes]
            [eng-bot.github-channel-gateway :as g]))

(s/def ::expected-channels (s/coll-of #(s/valid? ::g/channels %) :min-count 2 :max-count 2))

(deftest all-channels-test
  (expecting "sends single GET request to Slack"
    (stub-http/with-routes!
      routes/slack-channels
      (g/all-channels {:api-url uri :token "test"})
      (expect 1 (count (stub-http/recorded-requests server)))
      (expect "GET" (:method (first (stub-http/recorded-requests server))))))

  (expecting "channels conform to spec"
    (stub-http/with-routes!
      routes/slack-channels
      (let [channels (g/all-channels {:api-url uri :token "test"})]
        (expect ::expected-channels channels)))))

(s/def ::expected-messages (s/coll-of #(s/valid? ::g/message %) :min-count 100 :max-count 100))

(deftest channel-messages-test
  (expecting "sends single GET request to Slack"
    (stub-http/with-routes!
      routes/slack-channel-history
      (g/channel-messages {:api-url uri :token "test"} "eng-general")
      (expect 1 (count (stub-http/recorded-requests server)))
      (expect "GET" (:method (first (stub-http/recorded-requests server))))))

  (expecting "messages to conform to spec"
    (stub-http/with-routes!
      routes/slack-channel-history
      (let [messages (g/channel-messages {:api-url uri :token "test"} "eng-general")]
        (expect ::expected-messages messages)))))
