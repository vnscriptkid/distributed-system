# Cluster Coordination Service

## Setup zookeeper
- setup java: https://mkyong.com/java/how-to-set-java_home-environment-variable-on-mac-os-x/

- download zookeeper: https://zookeeper.apache.org/releases.html

- config: `conf/zoo.cfg`
```js
dataDir=/Users/vnscriptkid/Desktop/Services/apache-zookeeper-3.7.0-bin
clientPort=2181
```

- run server `zkServer.sh`
```console
zkServer.sh start-foreground
```

- run client `zkCli.sh`
```console
zkCli.sh
```
