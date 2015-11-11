call migrate bootstrap --force --env=test
call migrate up --env=test
SET PGCLIENTENCODING=utf-8
call psql -U postgres -d feedmeTest -a -f ../src/test/resources/test_script.sql
pause