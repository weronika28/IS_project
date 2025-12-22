# How to run this shit

NA WINDOWSIE

1. Trzeba miec zainstalowanego docker i minikube
```shell
    kubectl version --client
    minikube version
    docker version
```

2. Odpalic 
```shell
    minikube start --driver=docker 
```

3. Włączyć ingress
```shell
    minikube addons enable ingress
```

4. Odpalić cmd w tym katalogu
```shell
  kubectl apply -f namespace.yaml
  kubectl apply -R -f .
```