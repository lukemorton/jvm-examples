(ns eng-bot.github-channel-gateway-test
  (:require [eng-bot.github-channel-gateway :as g]
            [stub-http.core :as stub-http]
            [eng-bot.test-util.routes :as routes]))

(use 'clojure.test)

(deftest all-channels-returns-list
  (stub-http/with-routes!
    routes/slack-channels
    (is (not (empty? (g/all-channels {:api-url uri :token "test"}))))
    (is (= 2 (count (g/all-channels {:api-url uri :token "test"}))))
    (is (= "announcements" (get (first (g/all-channels {:api-url uri :token "test"})) :name)))))
