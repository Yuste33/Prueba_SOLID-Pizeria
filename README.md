# Prueba SOLID pizzeria

Este proyecto es una aplicación Java que gestiona autenticación de usuarios, pedidos y procesamiento de pagos de una pizzeria, 
utilizando una base de datos MySQL para almacenar la información. La estructura del proyecto sigue un enfoque modular, 
separando la lógica de negocio, la gestión de la base de datos y las pruebas unitarias.


## Enlace al repositorio

```
[https://github.com/MarcosAlonso05/Turing#](https://github.com/Yuste33/Prueba_SOLID-Pizeria)
```



## Requisitos Previos

- Docker
- Docker Compose (opcional, si usas archivo compose)
- MySQL Client (opcional, para conectarse directamente)

## Configuración de la Base de Datos

Este proyecto utiliza MySQL en un contenedor Docker. A continuación, los detalles de conexión:

- **Puerto:** `3306` (puerto predeterminado de MySQL)
- **Usuario:** `admin`
- **Contraseña:** `1234`
- **Nombre de la base de datos:** pizzeriadb

### Iniciar el contenedor MySQL

Para iniciar el contenedor MySQL con Docker, ejecuta el siguiente comando:

```bash
docker run --name mysql-pizzeria -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=pizzadb -p 3306:3306 -d mysql:8
