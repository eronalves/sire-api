(ns sire-api.systems
  (:require [com.stuartsierra.component :as component]
            [sire-api.ports.handler.pedestal-config :as pedestal-config]
            [sire-api.ports.handler.component-pedestal :as pedestal-component]
            [sire-api.ports.handler.routes :as routes]))

(defn default-system [env]
  (component/system-map

    :controller {:name "controller"}

    :pedestal
    (component/using
      (pedestal-component/pedestal (pedestal-config/config env))
      [:controller])))

(defn dev-system [env]
  (default-system env))

(defn prod-system [env]
  (default-system env))