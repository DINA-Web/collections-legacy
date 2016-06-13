PWD := $(shell pwd)
MVN := maven:3.3.9-jdk-8

.PHONY: dox build

build:
	@echo "Building with mvn, takes about 5 min"
	docker pull $(MVN)
	docker run -it --rm --name my-maven-project \
		-v $(PWD):/usr/src/mymaven \
		-w /usr/src/mymaven $(MVN) \
		mvn clean install
	@echo "Building Docker image"
	docker build .

dox:
	@echo "Rendering API Blueprint into HTLM documentation using aglio"
	docker pull humangeo/aglio
	docker run -ti --rm -v $(PWD)/:/docs humangeo/aglio \
		aglio -i apiary.apib -o collections-api-reference.html
	sudo chown $(USR):$(USR) collections-api-reference.html
	firefox collections-api-reference.html
