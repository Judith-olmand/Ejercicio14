# 🗄️ Ejercicio14 [ConexionOracleMaven] — Inserción en Tablas Relacionadas

Este proyecto Java gestionado con Maven automatiza la carga de datos en un esquema relacional de Oracle. La aplicación demuestra cómo insertar registros en tablas vinculadas por claves foráneas, gestionando primero las entidades maestras (`departamento`) y posteriormente las entidades dependientes (`empleado`).

## 📋 Descripción del Proyecto
El propósito de este programa es poblar un sistema de base de datos mediante la interacción directa con el usuario y procesos automatizados. El flujo de trabajo incluye:
1.  **Carga de Catálogo**: Inserción automática de departamentos predefinidos (RRHH, IT, Seguridad y Expediciones).
2.  **Registro Dinámico**: Un bucle interactivo que solicita datos de 5 empleados al usuario.
3.  **Cálculo de Identificadores**: Uso de consultas agregadas para determinar el siguiente `ID` disponible para cada nuevo empleado.

## 🎯 Funcionalidades Principales
Al ejecutarse, el programa realiza las siguientes acciones:
*   **Gestión de Departamentos**: Inserta registros base en la tabla maestra utilizando un `PreparedStatement` reutilizable.
*   **Bucle de Contratación**: Solicita por teclado el nombre, salario e ID de departamento para 5 nuevos integrantes.
*   **Integridad Referencial**: Asegura que cada empleado sea asignado a un `dep_id` existente en la tabla de departamentos.
*   **Control de Errores**: Implementa bloques `try-catch` específicos para cada fase (departamento, consulta de ID, inserción de empleado) evitando que un fallo individual detenga todo el proceso.

## 🏗️ Estructura del Proyecto
El proyecto mantiene la organización modular de Maven:

```text
Ejercicio14 [ConexionOracleMaven]/
│
├── 📁 src/
│   └── 📁 main/
│       ├── 📁 java/
│       │   └── 📁 org/example/
│       │       ├── ☕ DBConfig.java        # Proveedor de configuración JDBC
│       │       └── ☕ Main.java            # Lógica de inserción masiva y relacional
│       └── 📁 resources/
│           └── 📄 db.properties           # Credenciales de acceso externas
│
├── 📁 target/                             # Binarios generados por Maven
├── 📄 pom.xml                             # Gestión de dependencias (OJDBC11)
└── 📄 README.md                           # Documentación del proyecto
```

## 📄 Formato del Archivo de Entrada
El archivo `src/main/resources/db.properties` debe contener la configuración de acceso:
```properties
db.url=jdbc:oracle:thin:@localhost:1521:xe
db.user=tu_usuario
db.password=tu_contraseña
```

## 🚀 Compilación y Ejecución
### Requisitos
*   Java JDK 17 o superior.
*   Maven 3.8+.
*   Base de datos Oracle con las tablas `departamento` y `empleado` ya creadas.

### Comandos de Terminal
```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar el proceso de carga de datos
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## 🔧 Características Técnicas Implementadas
*   **Múltiples PreparedStatements**: Uso simultáneo de objetos de sentencia para optimizar diferentes consultas SQL en la misma conexión.
*   **Automatización de IDs**: Cálculo dinámico del `emp_id` mediante la función `MAX()` de SQL.
*   **Interacción con Scanner**: Captura de datos en tiempo de ejecución para una experiencia de usuario dinámica.
*   **Manejo de Excepciones**: Gestión de errores en cascada para identificar fallos de red o de integridad referencial.

## 🎮 Ejemplo de Uso Visual

**Interacción en Consola:**
```text
Ingrese el nombre del empleado
> Laura
Ingrese el salario del empleado
> 2850.50
Ingrese el id del departamento
> 20
Empleado añadido correctamente
```

---
**Autor:** Judith Olmedo Andrés  
*Ejercicio 14 - Gestión Relacional y Carga de Datos con JDBC y Oracle*