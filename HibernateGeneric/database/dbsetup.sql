CREATE DATABASE tutorials;
USE tutorials;
GRANT ALL ON tutorials.* to 'myuser'@'localhost' identified by 'mypassword';
commit;
flush privileges;