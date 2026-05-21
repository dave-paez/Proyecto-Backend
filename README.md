# 📚 Sistema de Gestión Académico Integrado - Backend

## 📖 Descripción General

Backend desarrollado en **Spring Boot 3.5.14** con **Java 21** para la gestión de proyectos académicos, participantes, recursos, patrocinios y mantenimiento de recursos. Utiliza **JPA/Hibernate** como ORM y **SQL Server** como base de datos relacional.

Este proyecto implementa un sistema de gestión completo con operaciones CRUD reales en base de datos, reemplazando el almacenamiento temporal en memoria por persistencia en BD relacional.

---

## 🚀 Guía de Setup Inicial

### Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

- **Java 21** o superior ([Descargar](https://www.oracle.com/java/technologies/downloads/#java21))
- **Maven 3.8+** ([Descargar](https://maven.apache.org/download.cgi))
- **SQL Server 2019** o superior con **SQL Server Express** (para desarrollo)
- **Git** (opcional, para control de versiones)
- **IDE recomendado**: IntelliJ IDEA, Eclipse o VS Code

### Paso 1: Clonar/Descargar el Proyecto

```bash
# Si tienes Git
git clone <repositorio-url>
cd Proyecto-Backend

# O descarga el archivo ZIP y extrae
```

### Paso 2: Verificar Variables de Entorno

Verifica que Java esté correctamente instalado:

```bash
java -version
# Debe mostrar: openjdk version "21.x.x"

mvn --version
# Debe mostrar: Apache Maven 3.8.x
```

### Paso 3: Instalar Dependencias

Desde la raíz del proyecto ejecuta:

```bash
mvn clean install
```

Este comando:
- Descarga todas las dependencias del proyecto
- Compila el código
- Ejecuta los tests (si los hay)

### Paso 4: Verificar la Estructura del Proyecto

```
Proyecto-Backend/
├── pom.xml                    # Configuración de Maven y dependencias
├── README.md                  # Esta documentación
├── mvnw / mvnw.cmd           # Maven Wrapper (para ejecutar sin Maven instalado)
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/proyecto/backend/
│   │   │       ├── App.java                     # Menú principal
│   │   │       ├── BackendApplication.java      # Configuración Spring Boot
│   │   │       ├── model/                       # Entidades JPA
│   │   │       └── repository/                  # Interfaces de persistencia
│   │   └── resources/
│   │       └── application.properties           # Configuración BD
│   └── test/
│       └── java/
│           └── BackendApplicationTests.java
└── target/                    # Artefactos compilados
```

---

## 🗄️ Configuración de Base de Datos

### Opción A: Usar Credenciales Existentes (Desarrollo Local)

Si tienes SQL Server ejecutándose localmente con las credenciales del proyecto, simplemente inicia la aplicación.

**Credenciales actuales** (archivo `application.properties`):
- **Servidor**: `LAPTOP-RMDCQ57I\SQLEXPRESS`
- **Usuario**: `gonzo`
- **Contraseña**: `1027885058`
- **Base de Datos**: `ProyectoIntegradorFinal`
- **Puerto**: `1433` (default)

### Opción B: Configurar Nuevas Credenciales

#### ⚠️ **Importante: Seguridad**

**NUNCA hagas commit de credenciales reales en Git.** Usa variables de entorno en producción.

#### Paso 1: Editar `application.properties`

Abre el archivo:
```
src/main/resources/application.properties
```

Modifica la configuración:

```properties
spring.application.name=backend

# Reemplaza estos valores con tus credenciales
spring.datasource.url=jdbc:sqlserver://<SERVIDOR>\<INSTANCIA>:<PUERTO>;databaseName=<BD_NAME>;trustServerCertificate=true;
spring.datasource.username=<TU_USUARIO>
spring.datasource.password=<TU_CONTRASEÑA>
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect
```

**Ejemplo si tienes SQL Server Express local con usuario `sa`:**

```properties
spring.datasource.url=jdbc:sqlserver://localhost\SQLEXPRESS:1433;databaseName=ProyectoIntegradorFinal;trustServerCertificate=true;
spring.datasource.username=sa
spring.datasource.password=tu_contraseña_aqui
```

#### Parámetros Explicados

| Parámetro | Descripción | Ejemplo |
|-----------|-------------|---------|
| `SERVIDOR` | Nombre del servidor SQL Server | `LAPTOP-ABC123` o `localhost` |
| `INSTANCIA` | Instancia de SQL Server | `SQLEXPRESS` (dev) o `MSSQLSERVER` (prod) |
| `PUERTO` | Puerto de conexión (default 1433) | `1433` |
| `BD_NAME` | Nombre de la base de datos | `ProyectoIntegradorFinal` |
| `USUARIO` | Usuario de BD (ej: `sa`, `gonzo`) | `sa` |
| `CONTRASEÑA` | Contraseña del usuario | `MiContraseña123!` |

### Opción C: Usar Variables de Entorno (Recomendado para Producción)

#### Paso 1: Define Variables de Entorno del Sistema

**En Windows:**
```
setx DB_SERVER LAPTOP-RMDCQ57I\SQLEXPRESS
setx DB_NAME ProyectoIntegradorFinal
setx DB_USER gonzo
setx DB_PASSWORD 1027885058
```

**En Linux/Mac:**
```bash
export DB_SERVER=localhost
export DB_NAME=ProyectoIntegradorFinal
export DB_USER=gonzo
export DB_PASSWORD=1027885058
```

#### Paso 2: Modifica `application.properties`

```properties
spring.datasource.url=jdbc:sqlserver://${DB_SERVER}:1433;databaseName=${DB_NAME};trustServerCertificate=true;
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

---

## 🗃️ Crear/Restaurar la Base de Datos

### Opción 1: Dejar que Hibernate Cree las Tablas (Automático)

**El proyecto está configurado con:**
```properties
spring.jpa.hibernate.ddl-auto=update
```

Esto significa que **Hibernate creará automáticamente todas las tablas** al iniciar la aplicación por primera vez. Simplemente ejecuta:

```bash
mvn spring-boot:run
```

O desde tu IDE, ejecuta `BackendApplication.java`.

**⚠️ Nota**: Esto crea las tablas, pero no inserta datos iniciales.

---

### Opción 2: Crear Tablas Manualmente con Script SQL

Si prefieres controlar completamente la creación de la BD, usa este script SQL:

#### Crear Archivo: `src/main/resources/schema.sql`

```sql
-- ============================================
-- CREAR BASE DE DATOS
-- ============================================
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'ProyectoIntegradorFinal')
BEGIN
    CREATE DATABASE ProyectoIntegradorFinal;
END
GO

USE ProyectoIntegradorFinal;
GO

-- ============================================
-- TABLA: USUARIOS (Verificación/Administrativos)
-- ============================================
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'USUARIOS')
BEGIN
    CREATE TABLE USUARIOS (
        USUARIO_ID VARCHAR(50) PRIMARY KEY,
        NOMBRE VARCHAR(150) NOT NULL,
        CORREO VARCHAR(120),
        CONTRASENA VARCHAR(100),
        TELEFONO VARCHAR(20)
    );
END
GO

-- ============================================
-- TABLA: PROYECTOS
-- ============================================
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'PROYECTOS')
BEGIN
    CREATE TABLE PROYECTOS (
        PROYECTO_ID VARCHAR(50) PRIMARY KEY,
        NOMBRE VARCHAR(200) NOT NULL,
        CORREO VARCHAR(120),
        TIPO VARCHAR(100),
        DESCRIPCION VARCHAR(500),
        FECHA_INICIO VARCHAR(20) NOT NULL,
        FECHA_FIN VARCHAR(20),
        ESTADO VARCHAR(50) NOT NULL
    );
END
GO

-- ============================================
-- TABLA: PARTICIPANTES
-- ============================================
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'PARTICIPANTES')
BEGIN
    CREATE TABLE PARTICIPANTES (
        PARTICIPANTE_ID VARCHAR(50) PRIMARY KEY,
        NOMBRE VARCHAR(150) NOT NULL,
        CORREO VARCHAR(120),
        UBICACION VARCHAR(200) NOT NULL,
        ROL VARCHAR(100) NOT NULL
    );
END
GO

-- ============================================
-- TABLA: PATROCINIO
-- ============================================
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'PATROCINIO')
BEGIN
    CREATE TABLE PATROCINIO (
        PATROCINADOR_ID VARCHAR(50) PRIMARY KEY,
        NOMBRE VARCHAR(150) NOT NULL,
        CORREO VARCHAR(120),
        CONTACTO VARCHAR(150) NOT NULL,
        TIPO VARCHAR(100),
        APORTE VARCHAR(200)
    );
END
GO

-- ============================================
-- TABLA: RECURSOS
-- ============================================
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'RECURSOS')
BEGIN
    CREATE TABLE RECURSOS (
        RECURSO_ID VARCHAR(50) PRIMARY KEY,
        NOMBRE VARCHAR(150) NOT NULL,
        CATEGORIA VARCHAR(100) NOT NULL,
        ESTADO VARCHAR(50) NOT NULL,
        UBICACION VARCHAR(200) NOT NULL,
        FECHA_INGRESO VARCHAR(20),
        PROYECTO_ID VARCHAR(50)
    );
END
GO

-- ============================================
-- TABLA: MANTENIMIENTO_RECURSOS
-- ============================================
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'MANTENIMIENTO_RECURSOS')
BEGIN
    CREATE TABLE MANTENIMIENTO_RECURSOS (
        MMTO_ID VARCHAR(50) PRIMARY KEY,
        NOMBRE_TECNICO VARCHAR(150) NOT NULL,
        DESCRIPCION VARCHAR(500),
        ESTADO VARCHAR(50),
        CATEGORIA VARCHAR(100),
        FECHA VARCHAR(20),
        RECURSO_ID VARCHAR(50)
    );
END
GO

-- ============================================
-- DATOS DE PRUEBA (OPCIONAL)
-- ============================================
-- Ejecuta estas líneas solo si quieres datos iniciales

-- Usuario administrativo de prueba
INSERT INTO USUARIOS VALUES ('ADMIN001', 'Administrador', 'admin@proyecto.edu', 'admin123', '5551234567');

-- Proyecto de prueba
INSERT INTO PROYECTOS VALUES ('PROY001', 'Plataforma Web', 'proyecto@edu.com', 'Web', 'Sistema de gestión académica', '2024-01-15', '2024-12-31', 'Activo');

-- Participante de prueba
INSERT INTO PARTICIPANTES VALUES ('PART001', 'Juan Pérez', 'juan@edu.com', 'Edificio A', 'Coordinador');

GO
```

#### Ejecutar el Script en SQL Server

**Opción 1: Desde SQL Server Management Studio (SSMS)**
1. Abre SSMS
2. Conecta a tu instancia de SQL Server
3. Abre una **Nueva Consulta**
4. Copia y pega el script SQL anterior
5. Presiona `F5` o `Ejecutar`

**Opción 2: Desde Command Line**
```bash
sqlcmd -S LAPTOP-RMDCQ57I\SQLEXPRESS -U gonzo -P 1027885058 -i schema.sql
```

**Opción 3: Desde PowerShell**
```powershell
$query = Get-Content "src/main/resources/schema.sql" -Raw
Invoke-SqlCmd -ServerInstance "LAPTOP-RMDCQ57I\SQLEXPRESS" -Username "gonzo" -Password "1027885058" -Query $query
```

---

### Opción 3: Restaurar desde Backup (Si tienes un .bak)

**En SSMS:**
1. Click derecho en **Databases** → **Restore Database...**
2. Selecciona el archivo `.bak`
3. Elige el destino y restaura

**En Command Line:**
```bash
sqlcmd -S LAPTOP-RMDCQ57I\SQLEXPRESS -U gonzo -P 1027885058 -Q "RESTORE DATABASE ProyectoIntegradorFinal FROM DISK = 'C:\backups\ProyectoIntegradorFinal.bak'"
```

---

## 🔧 Ejecutar la Aplicación

### Opción 1: Con Maven

```bash
mvn spring-boot:run
```

### Opción 2: Ejecutar desde IDE

1. Abre `BackendApplication.java`
2. Click derecho → **Run** (o presiona `Shift + F10` en IntelliJ)
3. La aplicación iniciará automáticamente el menú interactivo

### Opción 3: Crear JAR Ejecutable

```bash
mvn clean package
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

### Validar Conexión a BD

Cuando la aplicación inicie, verás en la consola:

```
[INFO] 2024-05-20 14:23:45.123  INFO 12345 --- [main] org.hibernate.dialect.Dialect : HHH000400: Using dialect: org.hibernate.dialect.SQLServerDialect
[INFO] 2024-05-20 14:23:46.456  INFO 12345 --- [main] c.p.b.BackendApplication : Started BackendApplication
```

Si ves estos mensajes **sin errores de conexión**, ✅ la BD está correctamente configurada.

---

## 📱 Menú Principal

Una vez iniciada la aplicación, verás:

```
BIENVENIDO AL SOFTWARE INSTITUCIONAL ACADEMICO 
1. iniciar sesion 
2. registrarse 
3. salir
```

**Credencial de prueba:**
- ID: `ADMIN001`
- Contraseña: `admin123`

(Estos datos se crean automáticamente si ejecutas el script SQL con datos de prueba)

---

## 🐛 Troubleshooting

### Problema: "Cannot connect to database"

**Solución:**
1. Verifica que SQL Server esté corriendo: `Services.msc` → busca **SQL Server**
2. Verifica las credenciales en `application.properties`
3. Prueba la conexión con SSMS manualmente

### Problema: "Port 1433 already in use"

**Solución:**
```bash
netstat -ano | findstr :1433  # Identifica el proceso
taskkill /PID <PID> /F        # Mata el proceso
```

### Problema: "Table does not exist"

**Solución:**
1. Verifica que `spring.jpa.hibernate.ddl-auto=update` esté en `application.properties`
2. Ejecuta el script SQL manualmente
3. Reinicia la aplicación

### Problema: "User not authenticated"

**Solución:**
- El usuario debe estar registrado previamente
- Usa el menú opción **2 (Registrarse)** primero
- O crea el usuario manualmente en la tabla `USUARIOS`

---

## 📚 Próximos Pasos

Una vez que la BD esté funcionando:

1. **Lee**: [Guía de Integración Frontend](./docs/INTEGRACION_FRONTEND.md) (próximamente)
2. **Explora**: Las clases en `src/main/java/com/proyecto/backend/`
3. **Customiza**: Los repositorios según tus necesidades
4. **Extiende**: Añade nuevas entidades o funcionalidades

---

## 📝 Notas Importantes

- **Credenciales seguras**: En producción, usa **variables de entorno**, no hardcodes
- **Backups regulares**: Realiza backups de la BD regularmente
- **Control de versiones**: Nunca hagas commit de `application.properties` con credenciales reales
- **Perfiles de configuración**: Considera crear `application-dev.properties`, `application-prod.properties`

---

## 📞 Soporte

Para problemas o preguntas:
- Revisa la sección **Troubleshooting** arriba
- Consulta la [documentación oficial de Spring Boot](https://spring.io/projects/spring-boot)
- Documentación de [Hibernate ORM](https://hibernate.org/)

---

**Última actualización**: Mayo 2024  
**Versión**: 1.0.0  
**Estado**: En desarrollo
