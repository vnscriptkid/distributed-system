# Load Balancer

## why?
- prevent bottle neck
- prevent single point of failure

## types?
- Hardware: dedicated machine, reliable, higher cost
- Software: 
  - running on commodity server, cheaper, flexible, easy to configure
  - nginx, HAProxy

## strategies?
- statistical
  - round-robin
    - problem: num of reqs !== actual load => cascading failures (domino)
  - weighted round-robin
  - source ip hash
    - session stickiness: reqs from same user goes to the same app servers
    - examples: shopping cart, video streaming
- active
  - least connections
    - most open conn ~ busy => receive less
  - weighted response time
    - periodic health check => long res time ~ busy
  - agent based
    - installed on each app server, send reports to LB on diff metrics.
