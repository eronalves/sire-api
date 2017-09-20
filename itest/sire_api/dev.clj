(ns sire-api.dev
    (:use midje.sweet)
    (:require
    [environ.core :refer [env]]
    [reloaded.repl :refer [system init start stop go reset reset-all]]))

(defn run-env [port]
  (go))

(defn stop-env []
    (stop))