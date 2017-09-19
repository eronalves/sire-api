(ns sire-api.ports.handler.routes-test
  (:use midje.sweet)
  (:require
    [com.stuartsierra.component :as component]
    [io.pedestal.test :refer :all]
    [cheshire.core :refer :all]
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

  (fact "Route '/' must be return a message"
     (cpt/with-system #'system init-fn
        (let [response (response-for (cpt/service system) :get "/")
              {:keys [body status headers]} response]
          status => 200
          "text/plain" => (get headers "Content-Type")
          body => "Welcome to Sire API!")))

   (facts "Pull requests routes")

     (fact "Route '/pull-requests' must be return a list of pull requests"
       (cpt/with-system #'system init-fn
          (let [response (response-for (cpt/service system) :get "/pull-requests")
                {:keys [body status headers]} response
                body-parsed (parse-string body true)]
            status => 200
            body-parsed => [{:id 1 :name "test"} {:id 2 :name "test2"}]))))