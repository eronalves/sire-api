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

(defn add-prefix-route [route]
  (str "/api/v1" route))

(facts "Application routes"

  (cpt/with-system #'system init-fn
    (fact "Route '/' must be return a message"
          (let [response (response-for (cpt/service system) :get "/")
                {:keys [body status headers]} response]
            status => 200
            (get headers "Content-Type") => "text/plain"
            body => "Welcome to Sire API!"))

   (fact "Pull requests routes"
     (fact "Route '/pull-requests' must be return a list of pull requests"
        (let [response (response-for (cpt/service system) :get (add-prefix-route "/pull-requests"))
              {:keys [body status headers]} response
              parsed-body (parse-string body true)]
          status => 200
          parsed-body => [{:id 1 :name "test"} {:id 2 :name "test2"}]))

     (fact "Route '/pull-requests/:id' must be return a pull request"
        (let [response (response-for (cpt/service system) :get (add-prefix-route "/pull-requests/1"))
              {:keys [body status headers]} response
              body-parsed (parse-string body true)]
          status => 200
          body-parsed => {:id "1" :name "test"}))

     (fact "Route '/pull-requests/:id/run' must be execute a pull request"
       (let [response (response-for (cpt/service system) :post (add-prefix-route "/pull-requests/1/run"))
             {:keys [body status headers]} response
             body-parsed (parse-string body true)]
         status => 200
         body-parsed => {:message (str "Pull request " 1 " running")}))

     (fact "Route '/pull-requests/:id/run' must be execute a pull request"
       (let [response (response-for (cpt/service system) :post (add-prefix-route "/pull-requests/1/stop"))
             {:keys [body status headers]} response
             body-parsed (parse-string body true)]
         status => 200
         body-parsed => {:message (str "Pull request " 1 " stopped")})))))