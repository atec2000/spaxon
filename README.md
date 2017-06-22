![Architecture](https://github.com/atec2000/spaxon/blob/master/architecture.png "Architecture")

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