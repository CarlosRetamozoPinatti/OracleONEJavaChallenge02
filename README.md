# LiterAlura

Este proyecto es una aplicación de gestión y búsqueda de libros y autores utilizando Java con el framework Spring Boot. Permite realizar diversas operaciones como buscar libros por título, listar libros y autores registrados, buscar autores vivos en un año determinado, listar libros por idioma, y mostrar estadísticas de la base de datos.

## Características

- **Buscar libro por título**: Permite buscar libros por su título utilizando una API externa.
- **Listar libros registrados**: Muestra una lista de todos los libros registrados en la base de datos.
- **Listar autores registrados**: Muestra una lista de todos los autores registrados en la base de datos.
- **Listar autores vivos en un año específico**: Permite buscar autores que estaban vivos en un año determinado.
- **Listar libros por idioma**: Muestra una lista de libros filtrados por idioma.
- **Listar libros por título**: Permite buscar libros por título en la base de datos.
- **Listar autores por nombre**: Permite buscar autores por nombre o apellido.
- **Mostrar estadísticas de la base de datos**: Muestra estadísticas generales de la base de datos, como el número total de libros y autores.

## Requisitos

- Java 11 o superior
- Spring Boot 2.6.2 o superior
- Maven 3.6.0 o superior

## Instalación

1. Clona el repositorio:

```bash
git clone https://github.com/CarlosRetamozoPinatti/OracleONEJavaChallenge02.git
```

2. Navega al directorio del proyecto:

```bash
cd LiterAlura
```

3. Construye el proyecto con Maven:

```bash
mvn clean install
```

## Ejecución

1. Ejecuta la aplicación:

```bash
mvn spring-boot:run
```

2. Interactúa con la aplicación a través del menú en la consola.

## Uso

Al ejecutar la aplicación, se presentará un menú en la consola con las siguientes opciones:

```
Elija el número de la opción deseada:

1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar libros por idioma
6 - Listar libros por título
7 - Listar autores por nombre
8 - Buscar los 5 libros más descargados
9 - Mostrar estadísticas de la base de datos

0 - Salir
```

### Ejemplos de uso

#### Buscar libro por título

Selecciona la opción `1` e ingresa el título del libro que deseas buscar. La aplicación realizará una solicitud a la API externa y mostrará los resultados.

#### Listar libros registrados

Selecciona la opción `2` para ver una lista de todos los libros registrados en la base de datos.

#### Listar autores registrados

Selecciona la opción `3` para ver una lista de todos los autores registrados en la base de datos.

#### Listar autores vivos en un año específico

Selecciona la opción `4` e ingresa el año para ver una lista de autores que estaban vivos en ese año.

#### Listar libros por idioma

Selecciona la opción `5` e ingresa el idioma para ver una lista de libros en ese idioma.

#### Listar libros por título

Selecciona la opción `6` e ingresa el título para buscar libros en la base de datos.

#### Listar autores por nombre

Selecciona la opción `7` e ingresa el nombre o apellido del autor que deseas buscar.

#### Buscar los 5 libros más descargados

Selecciona la opción `8` para ver una lista de los cinco libros más descargados.

#### Mostrar estadísticas de la base de datos

Selecciona la opción `9` para ver estadísticas generales de la base de datos.



