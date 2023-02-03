source .env && mvn clean package -Dmaven.test.skip && java -jar -Dspring.profiles.active=$PROFILE_ACTIVE target/backend.jar
