sudo docker ps -a
sudo docker rm --force "id"

sudo docker run -d --name=dev-consul -p 8500:8500 -e CONSUL_BIND_INTERFACE=eth0 consul