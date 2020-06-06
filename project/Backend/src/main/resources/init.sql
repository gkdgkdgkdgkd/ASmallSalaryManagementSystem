DROP DATABASE IF EXISTS app_test;
CREATE DATABASE app_test;
CREATE USER test_user@localhost identified by 'test_password';
GRANT ALL ON app_test.* to test_user@localhost;
