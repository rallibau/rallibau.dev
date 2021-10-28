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
start-frontend:
	@npm start --prefix frontend/blog/

start-gateway:
	@./gradlew :run --args="gateway server"
start-monolith:
	@./gradlew :run --args="monolit server"
start-bpm:
	@./gradlew :run --args="bpm server"
start-scheduler:
	@./gradlew :run --args="scheduler server"
start-acl:
	@./gradlew :run --args="acl server"
start-cms:
	@./gradlew :run --args="cms server"

start-bpm-compose:
	@./gradlew build --warning-mode all && docker-compose up -d --build bpm

start-schedule-compose:
	@./gradlew build --warning-mode all && docker-compose up -d --build schedule

stop-bpm-compose:
	@docker-compose stop bpm







