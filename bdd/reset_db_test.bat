call migrate bootstrap --force --env=test
call migrate up --env=test
call psql -U postgres -d feedmeTest -a -f ../src/test/resources/test_script.sql
pause