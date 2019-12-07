(ns eng-bot.github-post-message-gateway-test
  (:require [clojure.test :refer [deftest]]
            [expectations.clojure.test :refer [expect]]
            [stub-http.core :as stub-http]
            [eng-bot.test-util.routes :as routes]
            [eng-bot.github-post-message-gateway :as g]))

(deftest post-message-test
  (stub-http/with-routes!
    routes/slack-post-message
    (expect true (g/post-message {:api-url uri :token "test"} "eng-test" "HeY!"))))
