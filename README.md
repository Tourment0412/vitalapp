# VitalApp

**VitalApp** es una aplicación desarrollada en Java que tiene como objetivo principal gestionar información relacionada con pacientes y su historial médico. Utiliza Maven como herramienta de construcción y está diseñada para ejecutarse en un entorno de línea de comandos.

## 🧰 Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación principal.
- **Maven**: Herramienta de gestión y construcción de proyectos.
- **JUnit 5**: Framework para pruebas unitarias.
- **Lombok**: Biblioteca para reducir el código boilerplate en Java.
- **Azure DevOps Pipelines**: Integración y entrega continua (CI/CD).

## 📁 Estructura del Proyecto

```plaintext
vitalapp/
├── data/
│   └── ... (archivos relacionados con datos)
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── soft/
│                   └── saludvital/
│                       └── vitalapp/
│                           ├── application/
│                           │   └── Main.java
│                           ├── model/
│                           │   ├── Paciente.java
│                           │   └── HistorialMedico.java
│                           └── ... (otros paquetes y clases)
├── azure-pipelines.yml
├── pom.xml
└── README.md
```
## 🧰 Tecnologías Utilizadas

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/Tourment0412/vitalapp.git
   cd vitalapp
    ```
## ⚙️ Integración Continua

Se ha configurado una **pipeline en Azure DevOps** mediante el archivo `azure-pipelines.yml`. Esta pipeline realiza automáticamente las siguientes tareas en cada push a la rama `main`:

- ✅ **Compilación del proyecto**
- 🧪 **Ejecución de pruebas unitarias**
- 📦 **Publicación de artefactos generados**

Esto permite asegurar la calidad del código y la entrega continua de versiones funcionales del proyecto.

---

## 📝 Notas Adicionales

- 💡 **Lombok**: Asegúrate de tener configurado correctamente **Lombok** en tu entorno de desarrollo (por ejemplo, en IntelliJ o Eclipse) para evitar errores de compilación relacionados con getters, setters u otros métodos generados automáticamente.

- ☕ **Java 17**: El proyecto está configurado para usar **Java 17**. Verifica que esta versión esté instalada en tu máquina para poder compilar y ejecutar correctamente la aplicación.
