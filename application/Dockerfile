FROM openjdk:16.0

ENV APP_DIR /usr/telestion/telestion-project-workshop

ADD build/distributions/telestion-project-workshop.tar /usr/telestion

WORKDIR $APP_DIR
EXPOSE 9870

ENTRYPOINT ["sh", "-c"]
CMD ["./bin/telestion-project-workshop"]
