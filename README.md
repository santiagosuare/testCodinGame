# Test PLEXUS CodinGame
## Project in Java Spring Boot

### API's
- Consultar todos los Super Heroes.
- Consultar un unico Super Heroes por Id.
- Consultar todos los Super Heroes que contienen, en su nombre, el valor de un parametro. Ej: "man" devuelve "Superman", "Spiderman", "Batman".
- Modificar un Super Heroe.
- Eliminar un Super Heroe.


### Features

- Test unitarios de las API's.
- DB Relacional H2 en memoria.
- Libreria de Logs slf4j.
- Libreria de TimerLog para tomar el tiempo de ejecucion de una API.
- Gestion centralizada de excepciones.
- Aplicacion Dockerizada.
- Documentacion mediante Swagger.
- Seguridad con SpringSecurity.

## Librerias utilizadas

- [Spring Boot] - Spring Boot version 2.7.6
- [Maven] - Maven version 4.0.0
- [Swagger] - Swagger UI version 2.9.2

[Maven]: https://maven.apache.org/
[Spring Boot]: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
[Swagger]: https://swagger.io/specification/v2/

## Instalacion

> Note: This application requires [openjdk 11](https://openjdk.org/projects/jdk/11/) to run.

### `First build the image`
```sh
$ docker build -t testcodingame .   
```

### `Run the image in port 8080`
```sh
$ docker run -it -p 8080:8080 testcodingame    
```

## Swagger UI

Swagger dispone de una seguridad de JWT, implementada en la cual por el momento no cuenta con contraseña por defecto. Cualquier caracter lo toma como contraseña.
## License

Santiago Suarez

