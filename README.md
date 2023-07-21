1. Zookeepr docker run command
~~~
docker run --name springcloud-zookeeper --restart always -d -v "${pwd}/config/zoo.cfg:/conf/zoo.cfg" -v "${pwd}/data:/var/zookeeper/" -p 2181:2181 zookeeper
~~~

2. check docker ip address
~~~
docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' 容器名称或容器ID
~~~