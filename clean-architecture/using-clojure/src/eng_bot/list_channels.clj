(ns eng-bot.list-channels
  (:require [clojure.string :as str]
            [eng-bot.channels :as c]))

(defn- eng-channel?
  [channel]
  (str/starts-with? (get channel ::c/name) "eng-"))

(defn- eng-channels
  [channels]
  (filterv eng-channel? channels))

(defn exec
  [all-channels]
  (let [channels (all-channels)]
    (if (some #(= "eng-general" (get % ::c/name)) channels)
      (let [eng-general (first (filter #(= "eng-general" (get % ::c/name)) channels))]
        (->> channels
             (eng-channels)
             (remove #(= "eng-general" (get % ::c/name)))
             (sort-by ::c/name)
             (cons eng-general)))
      (->> channels
           (eng-channels)
           (sort-by ::c/name)))))
