# DINA-Web Collections API

[![Build Status](https://travis-ci.org/DINA-Web/collections-api.svg?branch=master)](https://travis-ci.org/DINA-Web/collections-api)
[![codecov.io](https://codecov.io/github/DINA-Web/collections-api/coverage.svg?branch=master)](https://codecov.io/github/DINA-Web/collections-api?branch=master)

The DINA-Web Collections API is an essential part of DINA-Web - "Digital Natural History Collections for the Web". It provides data services for museum collection management workflows.

# Overview

The Collections API is made up of three Java modules:
  
	- dina-data-service - exposes the Collections API through a RESTful service
	- dina-data-jpa - CRUD operations visavi the relational database
	- dina-datamodel - data model of entities from database schema
 
# Usage

Use the Makefile for building, generating documentation etc:

	# build using official mvn from Docker Hub
	make build

	# generate docs
	make dox

# Documentation

The Collections API reference documentation is here:

	- [Collections API reference](https://rawgit.com/DINA-Web/collections-api/master/collections-api-reference.html)

Guides, tutorials and usage examples:

	- [curl example](https://github.com/DINA-Web/dw-collections/blob/master/demo.sh)
	- ... more to follow



