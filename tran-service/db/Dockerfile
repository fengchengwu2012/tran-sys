FROM java:8
EXPOSE 8880
VOLUME /home/tran
ADD tran-service.jar /tran-app.jar
RUN bash -c 'touch /tran-app.jar'
ENTRYPOINT ["java","-jar","/tran-app.jar"]
