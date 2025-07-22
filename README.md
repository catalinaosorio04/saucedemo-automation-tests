🧪 SauceDemo Automation Tests
Este repositorio contiene una suite de automatización de pruebas para la aplicación e-commerce SauceDemo. El proyecto implementa pruebas automatizadas para las funcionalidades principales de la plataforma, asegurando la calidad y el correcto funcionamiento.
🚀 Tecnologías utilizadas
Lenguaje principal: Java

Frameworks de Testing:

Selenium WebDriver: Para la automatización de navegadores web.

Cucumber: Framework BDD (Behavior Driven Development) para escribir pruebas en lenguaje natural (Gherkin).

TestNG: Para la gestión y ejecución de pruebas, permitiendo una organización robusta.

Gestión de dependencias: Gradle

Herramientas adicionales:

WebDriverManager: Para la gestión automática de drivers de navegadores.

Page Object Model (POM): Arquitectura para mantener el código de pruebas organizado, reutilizable y fácil de mantener.

🧱 Arquitectura y Estructura del Proyecto
El proyecto está diseñado siguiendo el patrón Page Object Model (POM) para una mejor organización del código y reusabilidad. Además, se integra BDD con Cucumber para crear casos de prueba legibles y comprensibles para todos los miembros del equipo.

La estructura del repositorio es la siguiente:

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
│   │   │   ├── DriverManager.java        
│   │   │   └── ConfigReader.java           
│   └── resources/
│       └── features/
│           ├── login.feature               
│           ├── cart.feature              
│           └── e2e-shopping.feature     
🚀 Funcionalidades Probadas
Las pruebas automatizadas cubren las siguientes áreas clave de la aplicación SauceDemo:

Login de usuarios:

Validación de credenciales correctas.

Pruebas de login fallido con credenciales inválidas.

Validación de campos obligatorios.

Catálogo de productos:

Visualización y filtrado de productos.

Carrito de compras:

Funcionalidad para agregar productos al carrito.

Funcionalidad para eliminar productos del carrito.

Verificación del contador de productos en el carrito.

Validación de la información de los productos en el carrito.

Proceso de Checkout (E2E - End-to-End):

Flujo completo de compra, desde la selección de productos hasta la confirmación de la orden.

Validación de la información ingresada durante el checkout.

Confirmación exitosa de la orden.

📋 Casos de Prueba Implementados
A continuación, se detallan algunos de los casos de prueba específicos que se han automatizado:

Login
✅ Login exitoso con credenciales válidas: Verifica que un usuario con credenciales correctas pueda acceder al sistema.

✅ Login fallido con credenciales inválidas: Confirma que el sistema maneja correctamente credenciales erróneas y muestra el mensaje de error apropiado.

✅ Validación de campos obligatorios: Asegura que no se pueda iniciar sesión con campos de usuario o contraseña vacíos.

Carrito de Compras
✅ Agregar producto al carrito: Prueba la adición de un artículo al carrito.

✅ Eliminar producto del carrito: Verifica que los productos puedan ser eliminados del carrito.

✅ Verificar contador de productos: Asegura que el ícono del carrito muestre el número correcto de artículos.

✅ Validar información del producto en carrito: Confirma que los detalles del producto (nombre, precio) sean correctos en la página del carrito.

Checkout E2E
✅ Proceso completo de compra: Simula la experiencia de un usuario desde la selección de productos hasta la finalización de la compra.

✅ Validación de información de checkout: Verifica que los datos del usuario (nombre, apellido, código postal) sean aceptados correctamente.

✅ Confirmación de orden: Asegura que la orden sea confirmada exitosamente al final del proceso de checkout.

📂 Cómo usar este repositorio

Clona el repositorio: 

git clone https://github.com/catalinaosorio04/saucedemo-automation-tests.git
