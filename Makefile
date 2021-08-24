.PHONY: all build test ping-mysql

all: build

up:
	@docker-compose up -d

build:
	@./gradlew build --warning-mode all

run-tests:
	@./gradlew test --warning-mode all


run:
	@./gradlew :run
	

# Start the app
#set enviroment: set spring.profiles.active=produccion
start-gestionSaldo:
	@./gradlew :run --args="gestionSaldo server"

start-bpm:
	@./gradlew :run --args="bpm server"


