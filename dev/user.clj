(ns user
  (:require [reloaded.repl :refer [system init start stop go reset reset-all]]
            [sire-api.systems :refer [dev-system]]
            [environ.core :refer [env]]
            [clansi :refer [style]]))

(defmacro println-debug [& more]
  (apply println (style "DEBUG:" :red) more))

(reloaded.repl/set-init! #(dev-system {:port (read-string (env :http-port))}))
