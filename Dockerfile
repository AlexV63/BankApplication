FROM bellsoft/liberica-openjdk-alpine-musl:18

WORKDIR /app
RUN npm install -g maildev --unsafe-perm
CMD maildev & exec java -jar app.jar
