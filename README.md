SauceDemo Automation Tests
📋 Descripción
Suite de automatización de pruebas para la aplicación e-commerce SauceDemo. El proyecto implementa pruebas automatizadas para las funcionalidades principales de la plataforma.
🏗️ Arquitectura del Proyecto

Page Object Model (POM): Para mantener el código organizado y reutilizable
BDD (Behavior Driven Development): Con Cucumber para casos de prueba legibles
Selenium WebDriver: Para la automatización de pruebas web
TestNG: Para la gestión y ejecución de pruebas
Gradle: Como gestor de dependencias y build tool

🛠️ Tecnologías Utilizadas

Java: Lenguaje de programación
Selenium WebDriver: Automatización de navegadores web
Cucumber: Framework BDD para escribir pruebas en lenguaje natural
TestNG: Framework de testing para Java
Gradle: Herramienta de construcción y gestión de dependencias
WebDriverManager: Gestión automática de drivers de navegadores

📁 Estructura del Proyecto
src/
├── test/
│   ├── java/
│   │   ├── base/
│   │   │   └── BaseTest.java
│   │   ├── pages/
│   │   │   ├── BasePage.java
│   │   │   ├── LoginPage.java
│   │   │   ├── ProductsPage.java
│   │   │   ├── CartPage.java
│   │   │   └── CheckoutPage.java
│   │   ├── steps/
│   │   │   ├── CartStepDefinitions.java
│   │   │   ├── Hooks.java
│   │   │   └── LoginStepDefinitions.java
│   │   ├── runner/
│   │   │   └── TestRunner.java
│   │   └── utils/
│   │       ├── DriverManager.java
│   │       └── ConfigReader.java
│   └── resources/
│       └── features/
│           ├── login.feature
│           ├── cart.feature
│           └── e2e-shopping.feature
🚀 Funcionalidades Probadas

Login de usuarios: Validación de credenciales correctas e incorrectas
Catálogo de productos: Visualización y filtrado de productos
Carrito de compras: Agregar/eliminar productos del carrito
Proceso de checkout: Flujo completo de compra E2E

📋 Casos de Prueba Implementados
Login

✅ Login exitoso con credenciales válidas
✅ Login fallido con credenciales inválidas
✅ Validación de campos obligatorios

Carrito de Compras

✅ Agregar producto al carrito
✅ Eliminar producto del carrito
✅ Verificar contador de productos
✅ Validar información del producto en carrito

Checkout E2E

✅ Proceso completo de compra
✅ Validación de información de checkout
✅ Confirmación de orden


## 📂 Cómo usar este repositorio

1. Clona el repositorio:

clone https://github.com/catalinaosorio04/saucedemo-automation-tests.git

