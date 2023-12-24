FROM node:latest

EXPOSE 3000

WORKDIR /opt/frontend

COPY . /opt/frontend
ENV REACT_APP_BACKEND_URL=http://172.28.17.153:2346
RUN npm install
ENTRYPOINT npm start
