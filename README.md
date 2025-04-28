# QMSystem

QMSystem es una aplicación hecha en Spring Framework para gestation de colas.

## Documentación y respaldo

Se cuentan con archivos .bak, pdf y word en la siguiente ruta:

```code
/raiz_proyecto/manuales
/raiz_proyecto/backup-mssql
```

## Instalación

Después de restaurar el respaldo de la base de datos,

También necesitas cambiar la contraseña para usar usuarios existentes ve a la siguiente table e introduce un password ya cifrado con BCrypt, use el siguiente sitio https://bcrypt-generator.com

```code
dbo.gc_seguridad_usuario
```

Se han tomado estos usuarios, para pruebas de los roles

```code
ss.administrador
ss.operador
```

Despues de modificar los usuarios ahora hay que configurar las siguientes opciones en el archivo messages.properties para establecer los parámetros de la db, ejemplo:

```code
# ruta del fichero de configuración
src/main/resources/application.properties
```

```code
global.database.user=sa
global.database.password=password
global.database.driver=com.microsoft.sqlserver.jdbc.SQLServerDriver
global.database.url=jdbc:sqlserver://localhost:1433;databaseName=colas;
```

Descomprimir los recursos de video que se encuentran comprimidos en la carpeta 

```code
/raiz_proyecto/src/main/resources/static/contenido/contenidoi1.rar

# estraer solo los videos sin la carpeta que genera el rar en:

/raiz_proyecto/src/main/resources/static/contenido
```

Cuando establezca la creación del archivo .war defina el contexto en, esto es diferente en cada IDE en la sección de configuración de ejecución al momento de adjuntar el servidor de aplicaciones siendo en este caso un Wildfly 20:

```code
/colas
```

También se puede usar los comandos para generar el .war si no estas dentro de ningún IDE, y asegurandose que se genere colas.war como producto final teniendo en cuenta el contexto de ejecución antes mencionado.

```bash
# apache maven debe ya estar instalado y accesible desde la linea de comandos
mvn clean

mvn package
```

## Uso

Cuando carga la aplicación puedes usar los siguientes usuarios

```code
ss.administrador
ss.operador
```

## Contribución

Este es un proyecto heredado del cual se tiene parte de la documentación, sin embargo, el respaldo de la base de datos para este proyecto carece de muchos objetos los cuales se pretende ir creando a medida se depure el proyecto