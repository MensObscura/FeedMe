call migrate bootstrap --force
call migrate up
call psql -U postgres -d feedmeDev -a -f ../src/main/resources/dev_script.sql


pause