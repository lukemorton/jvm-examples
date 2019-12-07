(ns eng-bot.github-channel-gateway-test
  (:require [eng-bot.github-channel-gateway :as g]
            [stub-http.core :as stub-http]
            [eng-bot.test-util.routes :as routes]))

(use 'clojure.test)

(deftest all-channels-returns-list
  (stub-http/with-routes!
    routes/slack-channels
    (let [channels (g/all-channels {:api-url uri :token "test"})]
      (is (seq channels))
      (is (= 2 (count channels)))
      (is (= "announcements" (get (first channels) :name))))))
