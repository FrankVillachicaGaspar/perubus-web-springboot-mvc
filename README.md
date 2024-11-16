# Ejecución del proyecto

Para ejecutar el proyecto se requiere tener docker instalado en tu computadora. También debemos abrir una terminal
dentro de la carpeta del proyecto.

## Ejecución de archivo docker-compose.yml

Para ejecutar el docker compose debes ejecutar el siguiente comando.

```bash
docker-compose up -d --build
```

Esto contruirá la imagen de docker del proyecto y levantará el contenedor con la base de datos junto al proyecto.

## Poblar la base de datos

Caundo se haya levantado el contenedor de docker correctamente, ejecutaremos el siguiente comando.

```bash
# PowerShell
Get-Content .\src\main\resources\init.sql | docker exec -i postgresql-perubus psql -U postgres -d perubus_db

# Bash o WSL
docker exec -i postgresql-perubus psql -U postgres -d perubus_db < ./init.sql
```

## Posibles errores

Si la base de datos no se actualiza al iniciar el servicio web, entonces debes ir a docker desktop y reiniciar la
ejecución de la imagen **springboot-perubus**. De este modo forzaremos a que la base de datos se actualice y podremos
ejecutar el comando para poblar la base de datos.

## Abrir la aplicación web

Para abrir la pagina aplicación web debemos ir a la siguiente url:
[http://localhost:8080/home](http://localhost:8080/home)