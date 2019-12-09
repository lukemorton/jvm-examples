(set-env!
  :resource-paths #{"src"}
  :source-paths #{"test"}
  :dependencies '[[org.clojure/clojure "1.10.1"]
                  [lynxeyes/dotenv "1.0.2"]
                  [org.julienxx/clj-slack "0.6.3"]
                  [clj-time "0.15.2"]
                  [cheshire "5.9.0" :scope "test"]
                  [se.haleby/stub-http "0.2.7" :scope "test"]
                  [expectations/clojure-test "1.1.1" :scope "test"]
                  [tortue/spy "2.0.0" :scope "test"]
                  [adzerk/boot-test "1.2.0"]
                  [onetom/boot-lein-generate "0.1.3" :scope "test"]])

(require '[adzerk.boot-test :as boot-test])

(require 'boot.lein)
(boot.lein/generate)

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
