## How to start

run following commands

```
mvn clean package

mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

docker compose build

docker compose up -d
```
