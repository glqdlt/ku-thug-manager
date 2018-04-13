FROM java:8

RUN ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime

WORKDIR /
ADD ku-thug-manager-0.0.1.jar thug-manager.jar
ADD url.config url.config
ADD agent.config agent.config

EXPOSE 8080

CMD java -Xmx2048m -jar  -Durl.config.path=url.config -Dagent.config.path=agent.config thug-manager.jar