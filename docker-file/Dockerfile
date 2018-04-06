# Pull base image.
FROM java:8

WORKDIR /
ADD ku-thug-manager-0.0.1-SNAPSHOT.jar thug-manager.jar
ADD test.csv urls.csv

# Define default command.
CMD java -Xmx1024m -jar  -Durl.csv.path=urls.csv thug-manager.jar