(ns eng-bot.github-channel-gateway-test
  (:require [eng-bot.github-channel-gateway :as g]
            [clojure.spec.alpha :as s]
            [stub-http.core :as stub-http]
            [eng-bot.test-util.spec :refer [is-conforming-to-spec]]
            [eng-bot.test-util.routes :as routes]))

(use 'clojure.test)

(s/def ::name string?)
(s/def ::channels (s/keys :req-un [::name]))
(s/def ::expected-channels (s/coll-of #(s/valid? ::channels %) :min-count 2 :max-count 2))

(deftest all-channels-returns-list
  (stub-http/with-routes!
    routes/slack-channels
    (let [channels (g/all-channels {:api-url uri :token "test"})]
      (is-conforming-to-spec ::expected-channels channels))))

(s/def ::ts string?)
(s/def ::user string?)
(s/def ::text string?)
(s/def ::message (s/keys :req-un [::ts ::user ::text]))
(s/def ::expected-messages (s/coll-of #(s/valid? ::message %) :min-count 100 :max-count 100))

(deftest channel-history-returns-100-messages
  (stub-http/with-routes!
    routes/slack-channel-history
    (let [messages (g/channel-messages {:api-url uri :token "test"} "eng-general")]
      (is-conforming-to-spec ::expected-messages messages))))
