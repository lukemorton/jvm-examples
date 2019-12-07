(set-env!
  :resource-paths #{"src"}
  :source-paths #{"test"}
  :dependencies '[[org.clojure/clojure "1.10.1"]
                  [lynxeyes/dotenv "1.0.2"]
                  [org.julienxx/clj-slack "0.6.3"]
                  [cheshire "5.9.0"]
                  [se.haleby/stub-http "0.2.7"]
                  [adzerk/boot-test "1.2.0"]
                  [expectations/clojure-test "1.1.1"]
                  [tortue/spy "2.0.0"]])

(require '[adzerk.boot-test :as boot-test])

(deftask build-cli
  "Build CLI delivery mechanism"
  []
  (comp
        (aot :namespace '#{eng-bot.cli})
        (uber)
        (jar :file "eng-bot.jar" :main 'eng-bot.cli)
        (target)))

(deftask test
  "Run full test suite in watch mode"
  []
  (comp (watch)
        (notify :audible false
                :visual true
                :title "Test Results")
        (boot-test/test :include #".*-test")))
