(ns eng-bot.list-channels
  (:require [clojure.string :as str]))

(defn- eng-channel?
  [channel]
  (str/starts-with? (get channel :name) "eng-"))

(defn- eng-channels
  [channels]
  (filterv eng-channel? channels))

(defn exec
  [all-channels]
  (let [channels (all-channels)]
    (if (some #(= "eng-general" (get % :name)) channels)
      (->> channels
           (eng-channels)
           (remove #(= "eng-general" (get % :name)))
           (sort-by :name)
           (cons {:name "eng-general"}))
      (->> channels
           (eng-channels)
           (sort-by :name)))))
