# Consistent Hashing

## Problems with normal hashing (hashed-based sharding)
- hash(key) = key % N => where N is number of db nodes
- adding or removing node requires reallocation

## Problems solved
- Consistent hashing: node and keys on that node, are in the same space

## Techniques
- multiple hash fn
- virtual keys
