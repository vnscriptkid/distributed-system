# Replication

## replication vs sharding:
- sharding:
  - horizontal scaling

- replication:
  - create redundancy for higher availability, fault tolerant
  - master-slave arch

## replication models:
- master-slaves
- master-master

## eventual vs strict consistency:
- def:
  - eventual: one user updates data, it takes some time to others user can see new data. (temporarily see stale data)
  - strict: one user updates data, other users can see it immediately

- when to use what? trade-off, depends on specific use case
  - why it's trade-off?
    - strict: latency cost, update-to-date data
    - eventual: low latency, stale data temporarily
  - use cases:
    - strict: user account balance, avalable seats in theater, num of items in inventory
    - loose: number of views on video, number of subscribers ...
