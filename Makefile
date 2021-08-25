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
#set enviroment: set env=local
start-gestionSaldo:
	@./gradlew :run --args="gestionSaldo server"

start-bpm:
	@export env=local
	@./gradlew :run --args="bpm server" -Denv=compose-local

compose-bpm:
	@./gradlew build --warning-mode all && docker-compose up -d --build bpm






