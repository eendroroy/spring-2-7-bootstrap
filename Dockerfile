FROM amazoncorretto:11
RUN mkdir -p /data/sfa/jar /data/sfa/config /data/sfa/logs /data/sfa/tmp
ARG module
COPY ./target/$module.jar /data/sfa/jar/sfa.jar
ADD ./configs/docker.yaml /data/sfa/config/application.yaml
ENTRYPOINT [ \
    "java", \
    "-Djava.io.tmpdir=/data/sfa/tmp", \
    "-Dspring.config.location=file:/data/sfa/config/", \
    "-jar", \
    "/data/sfa/jar/sfa.jar" \
]
