echo "POST http://localhost:8002/micronaut-consumer/person" | vegeta attack -rate 500 -header "content-type:application-json" -body body.json -duration 50s | vegeta report -type=text
