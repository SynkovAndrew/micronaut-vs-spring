#!/bin/bash
for i in {1..100000}
do
   curl -X POST http://localhost:8080/person/random
done
