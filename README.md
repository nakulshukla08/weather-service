# weather-service



## Build

This project uses maven for build purpose. Run below command to run the build.

```bash
mvn clean install
```
Docker image is built by using docker-maven-plugin. Use the skip option to exclude image creation.


## Running the application

```bash
mvn spring-boot:run
```

or

```bash
docker run -d -p 8080:8080 weather-service
```

## Usage

```bash
curl -X POST \
  http://localhost:8080/weather/v2 \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Postman-Token: 3b52b98f-cb05-4473-bde4-31f711cfb505' \
  -H 'cache-control: no-cache' \
  -d 'city=london&apiKey=9703311f41bed24971c9e0f0a1d5c4f9&units=metric&lang=eng&cnt=24&undefined='
 ```
 
 APi used by thymelefe
 
 ```bash
 curl -X POST \
  http://localhost:8080/weather \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Postman-Token: 22b43b73-3cea-4bef-9fe5-2fdb24b343d5' \
  -H 'cache-control: no-cache' \
  -d 'city=london&apiKey=9703311f41bed24971c9e0f0a1d5c4f9&units=metric&lang=eng&cnt=24&undefined='
 ```