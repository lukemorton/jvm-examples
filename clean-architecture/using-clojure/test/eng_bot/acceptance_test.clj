(ns eng-bot.acceptance-test
  (:require [clojure.test :refer [deftest]]
            [expectations.clojure.test :refer [expect]]
            [eng-bot.test-util.routes :as routes]
            [stub-http.core :as stub-http]
            [eng-bot.factory :as factory]))

(deftest list-channels-returns-list-of-channels-with-names
  (stub-http/with-routes!
    routes/slack-channels
    (binding
      [factory/*slack-connection* {:api-url uri :token "test"}]
      (expect not (empty (get (first (factory/list-channels)) :name)))
      (expect "eng-general" (get (first (factory/list-channels)) :name)))))

(deftest share-channels-posts-list-of-channels
  (stub-http/with-routes!
    routes/slack-channels-and-post-message
    (binding
      [factory/*slack-connection* {:api-url uri :token "test"}]
      (expect true (factory/share-channels)))))
