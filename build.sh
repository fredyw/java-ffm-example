#!/bin/bash

(cd native; cargo fmt && cargo build --release) && ./gradlew build
