# Distributed System

## Centralized system
- what? one server, literally
- Scale up: add more ram, more CPU, more memory => vertical scaling.
- There is a limit, is not realistic to scale to inifinite.
- Single point of failure. One machine fails, the whole system fails.
- High latency: (1) one machine handles all reqs (2) reqs from other continent (long geographic distance).
- Security and Privacy: server exposed directly to internet.

## Distributed systems
- What? multiple servers, connected and know each other's state, all serving one purpose
- How? one process / machine
- Problems solved:
  - Scale up: **horizontal scaling**, adding more machines, no limit.
  - No single point of failure: ** fault tolerant**
  - Low latency: (1) reqs are shared by multiple servers (2) reqs are served from nearest server
  - Security and Privacy: servers stay behind proxy, better security and privacy. 
