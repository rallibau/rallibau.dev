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

sonar-qube:
	@./gradlew sonarqube -Dsonar.projectKey=rallibau.dev -Dsonar.host.url=http://localhost:9000 -Dsonar.login=94b26d5fb9a8faed257807297ee0cca63cf397b5

	

# Start the app
#set enviroment: export env=local
start-frontend:
	@npm start --prefix frontend/blog/

start-gateway:
	@./gradlew :run --args="gateway server"
start-monolit:
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








