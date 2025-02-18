#!/bin/bash

(cd native; cargo build --release) && ./gradlew build
