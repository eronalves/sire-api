{ :dev {:dependencies [[org.clojure/tools.namespace "0.2.11"]
                       [clansi "1.0.0"]
                       [ring/ring-mock "0.3.0"]
                       [midje "1.8.3"]
                       [reloaded.repl "0.2.3"]
                       [cheshire "5.8.0"]]
        :plugins [[lein-midje "3.2.1"]
                  [venantius/ultra "0.5.1"]]
        :source-paths ["dev"]
        :env {:http-port 3000}}

 :prod {:env {:http-port 8000
              :repl-port 8001}
        :dependencies [[org.clojure/tools.nrepl "0.2.12"]]}

 :uberjar {:aot :all}}