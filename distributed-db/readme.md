# Distributed Database

## replication, 
- why?
  - HA
  - Fault tolerant

- mongodb
  - replication set: variation of master-slave
  - master handle read, write reqs
  - slaves constantly keep data in sync with master (async)
  - if master goes down, new master is elected from slaves

- write semantics
  - why? it's problematic when master goes down before new data is synced with slaves
  - solution: write concern (2 or many) => proactively write to master and one or some slaves
    - pros: reliability
    - cons: write latency goes down

- read pref
  - modes:
    - primaryPreferred: first master, it not avail, go for slaves
    - secondary: too much for master already, go for secondary instead (accept not very up-to-date data) => eventual consistency
    - nearest: distribute replication set to diff geo location => read from nearest
      - pros: lower latency, higher throughput
      - cons: data consistency (eventual consistency)

## sharding
- why?
  - scalability => horizontal scaling

- mongodb
  - strategies:
    - hash based: hash key to determine which bucket to place into
      - pros: even distribution
      - cons: consecutive keys are not in the same shard   
    - range based: consecutive keys are likely to be in the same bucket
      - use case: range based queries
  - chunks:
    - distribute buckets to shards evenly
    - chunk splits when reaching size threshold
    - chunk migration and balancing
  - cluster:
    - router (mongos)
    - config server
    - prod env: each shard as replication set

## sharding design considerations
- is collection heavy-read or heavy-write
- what is the field on which user searches
- do users sort by field?
- is there any hotspot in our system (a lot of user queries on, example: famous movies)

## sql vs non-sql
- sql
  - structured, 
  - ACID compliant: atomicity, consistency, isolation, durability
    - JOIN
    - transaction
- non-sql
  - no structured
  - eventual consistency (update might not be reflected right away)
    - no way to query multiple collections at once
  - easy to scale, shard

## sharding in sql
- vertical: fewer columns
- horizontal: small subsets
