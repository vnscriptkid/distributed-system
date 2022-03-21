# Message Brokers

## why?
- direct communication needs both sides to be present (sync communication)

## features?
- message queue: one to one communication
- pub-sub: one to many communication

## use cases?

## kafka concepts
- producer record: { key, value, timestamp }
- topic:
  - category of messages
  - can have many partitions (each is just a queue)
- consumer group
