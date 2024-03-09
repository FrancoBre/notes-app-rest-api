docker build -t mysql_notes_db .
docker run -d --name mysql_notes_container -p 3306:3306 mysql_notes_db
docker start mysql_notes_container
