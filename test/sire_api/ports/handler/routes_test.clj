(ns sire-api.ports.handler.routes-test
  (:use midje.sweet)
  (:require
    [com.stuartsierra.component :as component]
    [io.pedestal.test :refer :all]
    [sire-api.pedestal-component-test-util :as cpt]
    [io.pedestal.http :as http]
    [sire-api.ports.handler.component-pedestal :as pedestal-component]
    [sire-api.ports.handler.pedestal-config :as pedestal-config]))

(def system nil)
(def env {:port 3000})

(defn test-system
  "Set up using PedestalServlet component instead of Pedestal"
  [env]
  (component/system-map

    :controller {:name "controller"}

    :pedestal
    (component/using
      (pedestal-component/pedestal (pedestal-config/config env))
      [:controller])))

(defn init-fn []
  (constantly (test-system env)))

(facts "Application routes"
  (fact "Home page must be return a message"
     (cpt/with-system #'system init-fn
                      (let [response (response-for (cpt/service system) :get "/")
                            {:keys [body status headers]} response]
                        status => 200
                        "text/plain" => (get headers "Content-Type")
                        body => "Welcome to Sire API!"))))