# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.155.1/containers/java-8/.devcontainer/base.Dockerfile

FROM openjdk:slim-buster

ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update && apt-get install -y \
    libfreetype6-dev    \
    vim                 \
    git

RUN useradd -ms /bin/bash vscode
RUN git config --global user.name paddymills
RUN git config --global user.email paddymills@pm.me

USER vscode