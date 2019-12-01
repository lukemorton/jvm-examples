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
      (is (not (empty (get (get (factory/list-channels) 0) :name))))
      (is (= "eng-general" (get (get (factory/list-channels) 0) :name))))))
