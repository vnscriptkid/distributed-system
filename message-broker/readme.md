# Message Brokers

## why?
- direct communication needs both sides to be present (sync communication)

## features?
- message queue: one to one communication
- pub-sub: one to many communication

## use cases?

## kafka
- is distributed system itself
  - perf, horizontal scaling
  - fault tolerant, HA
- producer record: { key, value, timestamp }
- topic:
  - category of messages
  - can have many partitions 
    - each is just a queue 
    - horizontal scale, global ordering is not possible with multiple partitions (trade-off)
    - works in parallel
- consumer group

## Q
- what decides parallelism of kafka?

## demo
```bash
# start zookeeper server
bin/zookeeper-server-start.sh config/zookeeper.properties

# start 1 kafka broker on localhost:9092
bin/kafka-server-start.sh config/server.properties

# create `chat` topic for the kafka server
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic chat

# list all topics of the server
bin/kafka-topics.sh --list --bootstrap-server localhost:9092

# start a producer and push messages to chat topic
bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic chat

# start a consumer and pull messages from chat topic from beginning
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic chat --from-beginning
```

## demo 2: multi-servers cluster
```bash
# start zookeeper server
bin/zookeeper-server-start.sh config/zookeeper.properties

# start 1 kafka broker on localhost:9092
bin/kafka-server-start.sh config/server.properties

# start 1 kafka broker on localhost:9093
bin/kafka-server-start.sh config/server-1.properties

# start 1 kafka broker on localhost:9094
bin/kafka-server-start.sh config/server-2.properties

# create `purchases` topic, specifying bootstrap server, that in turn populate the topic to all brokers across cluster
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 3 --topic purchases

# list all topics of the server
bin/kafka-topics.sh --list --bootstrap-server localhost:9092

# describe topic `purchases`
bin/kafka-topics.sh --describe --bootstrap-server localhost:9092 --topic purchases

# start a producer and push messages to over cluster on `purchases` topic
bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic purchases

# start a consumer and pull messages from `purchases` topic of cluster from beginning
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic purchases --from-beginning
```
