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
- 

## Q
- what decides parallelism of kafka?
