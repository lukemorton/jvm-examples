(ns eng-bot.github-post-message-gateway-test
  (:require [eng-bot.github-post-message-gateway :as g]
            [eng-bot.factory :as factory]
            [stub-http.core :as stub-http]
            [eng-bot.test-util.routes :as routes]))

(use 'clojure.test)

(deftest post-message-test
  (stub-http/with-routes!
    routes/slack-post-message
    (is (= true (g/post-message {:api-url uri :token "test"} "eng-test" "HeY!")))))
