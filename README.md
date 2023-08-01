# OAuth2-Keycloak
version: '3.9'

services:
  postgres:
    image: postgres:13.2
    restart: unless-stopped
    environment:
      POSTGRES_DB: ${POSTGRESQL_DB}
      POSTGRES_USER: ${POSTGRESQL_USER}
      POSTGRES_PASSWORD: ${POSTGRESQL_PASS}
    networks:
      - local-keycloak

  keycloak:
    depends_on:
      - postgres
    container_name: local_keycloak
    environment:
      KEYCLOAK_USER: admin 
      KEYCLOAK_PASSWORD: admin
      DB_VENDOR: postgres
      DB_ADDR: postgres
      DB_DATABASE: ${POSTGRESQL_DB}
      DB_USER: ${POSTGRESQL_USER}
      DB_PASSWORD: ${POSTGRESQL_PASS}
    image: jboss/keycloak:${KEYCLOAK_VERSION}
    ports:
      - "28080:8080"
    restart: unless-stopped
    volumes:
      - keycloak_data:/opt/jboss/keycloak/standalone/data
    networks:
      - local-keycloak

networks:
  local-keycloak:
volumes:
  keycloak_data:
