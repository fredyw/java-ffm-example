#!/bin/bash

(cd native; cargo test) && ./gradlew test
