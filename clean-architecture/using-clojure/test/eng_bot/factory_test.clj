(ns eng-bot.factory-test
  (:require [clojure.test :refer [deftest]]
            [expectations.clojure.test :refer [expect]]
            [clojure.string :as str]
            [eng-bot.factory :as factory]))

(deftest slack-connection-config-loaded-from-dotenv
  (expect ::factory/slack-connection factory/*slack-connection*))
