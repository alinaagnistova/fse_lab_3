FROM node:latest

EXPOSE 3000

WORKDIR /opt/frontend
ENV REACT_APP_BACKEND_URL=http://web04.cosmosway.ru:8181
COPY . /opt/frontend
RUN npm install
ENTRYPOINT npm start
