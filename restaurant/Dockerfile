FROM adoptopenjdk:12-openj9
WORKDIR /var/foodie/restaurant
ADD build/distributions/restaurant/ .
ADD src/config .
CMD /var/foodie/restaurant/bin/restaurant server /var/foodie/restaurant/config/docker-config.yml
EXPOSE 8080