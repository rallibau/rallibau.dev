.PHONY: all build test ping-mysql

all: build

up:
	@docker-compose up -d

build:
	@gradlew build --warning-mode all

run-tests:
	@gradlew test --warning-mode all

test:
	@docker exec inetum-livetooling ./gradlew test --warning-mode all

run:
	@gradlew :run
	

# Start the app
#set enviroment: set spring.profiles.active=produccion
start-gestionSaldo:
	@gradlew :run --args="gestionSaldo server"

start-gestionLista:
	@gradlew :run --args="gestionLista server"

start-gestionViaje:
	@gradlew :run --args="gestionViaje server"

start-livetooling:
	@gradlew :run --args="livetooling server"

start-tagreader:
	@gradlew :run --args="tagReader server"


