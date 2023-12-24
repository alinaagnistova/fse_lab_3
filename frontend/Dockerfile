FROM node:latest

EXPOSE 3000

WORKDIR /opt/frontend

COPY . /opt/frontend
ENV REACT_APP_BACKEND_URL=http://127.0.0.1:8181
RUN npm install
ENTRYPOINT npm start
