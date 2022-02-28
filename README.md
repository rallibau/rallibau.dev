# ☕🚀 Rallibau.dev

## 🏁 How To Start from visual

1. Install Java 11: `brew cask install corretto`
2. Set it as your default JVM: `export JAVA_HOME='/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home'`
3. Clone this repository: `git clone https://github.com/CodelyTV/java-ddd-skeleton`.
4. Bring up the Docker environment: `make up`.
5. Execute some [Gradle lifecycle tasks](https://docs.gradle.org/current/userguide/java_plugin.html#lifecycle_tasks) in order to check everything is OK:
    1. Create [the project JAR](https://docs.gradle.org/current/userguide/java_plugin.html#sec:jar): `make build`
    2. Run the tests and plugins verification tasks: `make test`
6. Start developing!





Dependency:
https://www.javainuse.com/spring/boot-jwt
https://bpmn.io/
https://github.com/facebook/create-react-app
https://es.reactjs.org/docs/getting-started.html
https://dev.to/surajondev/4-best-ui-framework-for-reactjs-c20
https://medium.com/technoetics/create-basic-login-forms-using-react-js-hooks-and-bootstrap-2ae36c15e551

https://earthly.dev/blog/monorepo-with-bazel/



argocd and k8s

iniciar argocd:  minikube service argocd-server -n argocd
Set admin password:
kubectl -n argocd patch secret argocd-secret \
-p '{"stringData": {
"admin.password": "$2a$10$0F1oETpELme5n23AHO26T.jg1GQv0VuhtoHM4UtXM0D4NP9LXUCU.",
"admin.passwordMtime": "'$(date +%FT%T%Z)'"
}}'
password encrypt: https://www.browserling.com/tools/bcrypt

minikube start
minikube dashboard
minikube service list

k8s rabbit:
https://www.rabbitmq.com/kubernetes/operator/install-operator.html
kubectl -n default get secret rallibau-rabbitmq-default-user -o jsonpath="{.data.username}" | base64 --decode
kubectl -n default get secret rallibau-rabbitmq-default-user -o jsonpath="{.data.password}" | base64 --decode
