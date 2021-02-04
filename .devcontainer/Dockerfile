# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.155.1/containers/java-8/.devcontainer/base.Dockerfile

FROM openjdk:slim-buster

ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update && apt-get install -y \
    vim \
    git

RUN useradd -ms /bin/bash vscode

USER vscode