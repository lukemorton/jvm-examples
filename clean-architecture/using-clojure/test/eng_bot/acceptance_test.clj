(ns eng-bot.acceptance-test
  (:require [eng-bot.factory :as factory]
            [stub-http.core :as stub-http]
            [eng-bot.test-util.routes :as routes]))

(use 'clojure.test)

(deftest list-channels-returns-list-of-channels-with-names
  (stub-http/with-routes!
    routes/slack-channels
    (binding
      [factory/*slack-connection* {:api-url uri :token "test"}]
      (is (not (empty (get (first (factory/list-channels)) :name))))
      (is (= "eng-general" (get (first (factory/list-channels)) :name))))))

(deftest share-channels-posts-list-of-channels
  (stub-http/with-routes!
    routes/slack-channels-and-post-message
    (binding
      [factory/*slack-connection* {:api-url uri :token "test"}]
      (is (= true (factory/share-channels))))))
