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

#start backend local
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

#start backend compose
start-bpm-compose:
	@./gradlew build --warning-mode all && docker-compose up -d --build bpm
start-schedule-compose:
	@./gradlew build --warning-mode all && docker-compose up -d --build schedule
start-acl-compose:
	@./gradlew build --warning-mode all && docker-compose up -d --build acl
start-cms-compose:
	@./gradlew build --warning-mode all && docker-compose up -d --build cms

#stop
stop-bpm-compose:
	@docker-compose stop bpm
stop-schedule-compose:
	@docker-compose stop schedule
stop-acl-compose:
	@docker-compose stop acl
stop-cms-compose:
	@docker-compose stop cms


#k8s
start-stage-infra:
	@pwd @eval $(minikube docker-env) && docker-compose build mysql && kubectl apply -f k8s-infrastructure/stage/persistence/secret.yaml && kubectl apply -f k8s-infrastructure/stage/persistence/persistentVolumeClaim.yaml && kubectl apply -f k8s-infrastructure/stage/persistence/mysql-deployment.yaml && kubectl apply -f k8s-infrastructure/stage/persistence/service.yaml
start-stage-services:
	@eval $(minikube docker-env) && docker-compose build acl && kubectl apply -f k8s-infrastructure/stage/acl/acl-deployment.yaml