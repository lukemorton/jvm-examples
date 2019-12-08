(ns eng-bot.factory-test
  (:require [clojure.test :refer [deftest function?]]
            [expectations.clojure.test :refer [expecting expect more->]]
            [dotenv :refer [env]]
            [eng-bot.factory :as factory]))

(deftest slack-connection-test
  (expecting "config to conform to spec"
    (expect ::factory/slack-connection factory/*slack-connection*))
  (expecting "config to be loaded from dotenv"
    (expect (more-> (env "SLACK_URL") :api-url
                    (env "SLACK_TOKEN") :token)
      factory/*slack-connection*)))

(deftest list-channels-test
  (expecting "to be defined"
    (expect function? factory/list-channels)))

(deftest identify-hottest-channels-test
  (expecting "to be defined"
    (expect function? factory/identify-hottest-channels)))

(deftest share-channels-test
  (expecting "to be defined"
    (expect function? factory/share-channels)))
