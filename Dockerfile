FROM adoptopenjdk:12-openj9
WORKDIR /var/foodie/order
ADD build/distributions/order/ .
ADD src/dist/config/default-config.yml .
CMD /var/foodie/order/bin/order server /var/foodie/order/config/default-config.yml
EXPOSE 8080