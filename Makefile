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
#set enviroment: export env=local

start-bpm:
	@./gradlew :run --args="bpm server"

start-bpm-compose:
	@./gradlew build --warning-mode all && docker-compose up -d --build bpm

stop-bpm-compose:
	@docker-compose stop bpm







