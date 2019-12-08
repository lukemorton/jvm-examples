(ns eng-bot.cli-test
  (:require [clojure.test :refer [deftest]]
            [expectations.clojure.test :refer [expecting expect]]
            [spy.core :refer [spy called-once?]]
            [eng-bot.cli :as cli]
            [eng-bot.factory :as factory]))

(deftest -main-test
  (expecting "calls share-channels"
    (with-redefs [factory/share-channels (spy)]
      (cli/-main "share-channels")
      (expect called-once? factory/share-channels))))
