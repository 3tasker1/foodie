FROM adoptopenjdk:12-openj9
WORKDIR /var/foodie/order
ADD build/distributions/order/ .
ADD src/config .
CMD /var/foodie/order/bin/order server /var/foodie/order/config/docker-config.yml
EXPOSE 8080