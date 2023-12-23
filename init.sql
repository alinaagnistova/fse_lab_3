create database web04;
alter user postgres with encrypted password 'postgres';
grant all privileges on database web04 to postgres;
