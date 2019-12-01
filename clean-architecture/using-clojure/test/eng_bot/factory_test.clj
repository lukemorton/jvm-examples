(ns eng-bot.factory-test
  (:require [eng-bot.factory :as factory]
            [clojure.string :as str]))

(use 'clojure.test)

(deftest slack-connection-config-loaded-from-dotenv
  (is (not (str/blank? (get factory/*slack-connection* :api-url))))
  (is (not (str/blank? (get factory/*slack-connection* :token)))))
