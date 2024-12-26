# gogo-travel

### Class Diagram
![Class Diagram](./docs/class-diagram.png)

### run application
`sudo docker run -d \
  --name gogo-travel \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgresadmin \
  -e POSTGRES_DB=GOGO-Travel \
  -e KAFKA_BOOTSTRAP_SERVER=52.64.172.62 \
  -p 8080:8080 \
  --network gogo_postgres \
  --restart unless-stopped \
  thanhloc1087/gogo-travel:0.6.0`