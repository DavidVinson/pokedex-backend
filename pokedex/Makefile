run:
# 	./gradlew formatKotlin
	./gradlew bootRun

clean:
	./gradlew clean

build:
	./gradlew formatKotlin
	./gradlew build

check:
	./gradlew check

# lint:
# 	./gradlew lintKotlin
#
# format:
# 	./gradlew formatKotlin
#
# dependencies:
# 	./gradlew -q dependencies infrastructure:dependencies
#
db/start:
	docker-compose -f docker/docker-compose.yml up -d

db/stop:
	docker-compose -f docker/docker-compose.yml down -v

db/migrate:
	./gradlew :update