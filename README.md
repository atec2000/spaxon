![Architecture](https://github.com/atec2000/spaxon/blob/master/architecture.png "Architecture")

IMPORTANT: This project is a demo project. For development convenience, subproject should be a entire project to utilize spring devtools to restart one of applications when classes recompiled.

#Running the Demo

##Environment
Java SDK 8
Docker (I'm using v17.03.1-ce)
Docker-compose (I'm using v1.11.2)

##Step 1: Build the containers
In a new empty folder, at the terminal execute the following command to download the latest code for this demo.
```bash
$ git clone https://github.com/atec2000/spaxon.git
```

Then build the docker container images.
```bash
$ cd spaxon
$ ./gradlew clean image
```

##Step 2: Start the Microservices
```bash
$ docker-compose -f wip.yml up
```

If you want to see which docker instances are running on your machine at any time, open a separate terminal and execute the following command:-
```bash
$ docker ps
```

If you want to remove stopped service containers:
```bash
$ docker-compose -f wip.yml rm -v
```

##Step 3: Integration Test (Manual)
```bash
$ curl -X POST -v --header "Content-Type: application/json" --header "Accept: */*" "http://localhost:18080/commands/products/add/1?name=Everything%20Is%20Awesome"
$ curl -X POST -v --header "Content-Type: application/json" --header "Accept: */*" "http://localhost:18080/commands/products/add" -d '{"name":"product name 1","saleable":"true"}'
$ curl -X POST -v --header "Content-Type: application/json" --header "Accept: */*" "http://localhost:18080/commands/products/add" -d '{"name":"product name 1","saleable":"true","productImages":[{"name":"name 1","url":"url 1"}]}'
```

The response code should be `HTTP/1.1 201 Created`. This means that the MP3 product "Everything is Awesome" has been added to the command-side event-sourced repository successfully.

Now lets check that we can view the product that we just added. To do this we use the query-side API and issue a simple 'GET' request.

```bash
$ curl http://localhost:18080/queries/products/1
```

##Step 4: Mongo check (Manual)
```bash
$ docker exec -it spaxon-mongodb mongo
> use products
> db.events.find()
```

##Step 5: MySQL check (Manual)
```bash
$ docker exec -it spaxon-mysql mysql -h localhost -u root -p
```

##Step 6: RabbitMQ check (Manual)
Check it by http://localhost:15672
