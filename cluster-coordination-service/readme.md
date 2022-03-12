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

- CLI on client
```console
ls /

create /parent "data for parent"
ls /
get /parent

create /parent/child "data for child"
ls /
ls /parent
get /parent/child

deleteall /parent

create /election ""
```

## Compile java
- install mvn: https://maven.apache.org/install.html | `brew install maven`
- compile: `maven-compiler-plugin`
- assemble: `maven-assembly-plugin`
- package: `nvm clean package`
- run packaged file: `java -jar file.jar`
