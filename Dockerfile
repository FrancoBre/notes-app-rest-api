# Use the official MySQL image from Docker Hub
FROM mysql:latest

# Set environment variables for MySQL database
ENV MYSQL_DATABASE=notes_app \
    MYSQL_USER=notes_user \
    MYSQL_PASSWORD=notes_password \
    MYSQL_ROOT_PASSWORD=root_password

# Copy the SQL schema script into the Docker container
COPY ./schema.sql /docker-entrypoint-initdb.d/

# Expose the MySQL port
EXPOSE 3306

