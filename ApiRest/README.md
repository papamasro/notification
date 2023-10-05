

Introducción
---


¡Bienvenido al repositorio del proyecto Tenpo Challenge! Este proyecto está desarrollado utilizando Spring.



Ejecutar el proyecto localmente
--

Ejecutar JAVA localmente:

Primero, asegúrate de tener instalado Java 17, IntelliJ y Git.
Clona el repositorio https://github.com/papamasro/taxChallenge.


API
--

Send Message:
--

Método: POST
URL: {{url}}/api/send

Request:

~~~
{
    "userId":"1",
    "type":"Status",
    "message":"This is a test."
}
~~~

Respuesta:
~~~
{
    "userId":"1",
    "type":"Status",
    "message":"This is a test."
}
~~~

Mejoras Obligatorias
---

Integración de JWT: Implementar JWT (JSON Web Tokens) para una autenticación y autorización seguras.

Integración de Micrometer y Grafana: Integrar Micrometer con Grafana para un monitoreo avanzado y la recopilación de métricas.

Builders: Implementar builders en vez de constructores.


Mejoras Opcionales
--
Integración de Lombok: Considerar integrar Lombok para reducir el código repetitivo en el proyecto.

MapStruct: Explorar la posibilidad de utilizar MapStruct para simplificar el mapeo.

Documentación con Swagger: La documentación de Swagger se encuentra en construcción, puedes verla con la configuración básica en: http://localhost:8080/swagger-ui/index.html
