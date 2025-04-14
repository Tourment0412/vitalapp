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
