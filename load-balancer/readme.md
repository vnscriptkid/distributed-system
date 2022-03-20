# Load Balancer

## why?
- prevent bottle neck
- prevent single point of failure

## types?
- Hardware: dedicated machine, reliable, higher cost
- Software: 
  - running on commodity server, cheaper, flexible, easy to configure
  - nginx, HAProxy
  - modes:
    - layer 4: simple LB logic (based TCP packets)
    - layer 7: more control over LB logic

<img width="600" alt="image" src="https://user-images.githubusercontent.com/28957748/159158714-91c16caf-1641-4750-b47c-738224ecd0f5.png">

## strategies?
- statistical
  - round-robin
    - problem: num of reqs !== actual load => cascading failures (domino)
  - weighted round-robin
  - source ip hash
    - session stickiness: reqs from same user goes to the same app servers
    - examples: shopping cart, video streaming (maintain streaming session)
- active
  - least connections
    - most open conn ~ busy => receive less
  - weighted response time
    - periodic health check => long res time ~ busy
  - agent based
    - installed on each app server, send reports to LB on diff metrics.
