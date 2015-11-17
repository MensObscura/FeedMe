call migrate bootstrap --force
call migrate up
SET PGCLIENTENCODING=utf-8
call psql -U postgres -d feedmeDev -a -f ../src/main/resources/dev_script.sql


pause