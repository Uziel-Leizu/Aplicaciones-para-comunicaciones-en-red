# Práctica 3 - Servidores Web

## Objetivo

Implementar servidores web HTTP/1.0 desde cero, comprendiendo el protocolo HTTP, el manejo de conexiones concurrentes y la E/S no bloqueante.

## Conceptos Cubiertos

- **Protocolo HTTP/1.0** — Métodos GET, POST, HEAD, DELETE
- **Pool de hilos** — Manejo de múltiples clientes con `ExecutorService`
- **Java NIO** — E/S no bloqueante con `Selector`, `SocketChannel`, `ServerSocketChannel`
- **Tipos MIME** — Identificación de tipos de contenido por extensión
- **Cabeceras HTTP** — Content-Type, Content-Length, Date

---

## Proyectos

### 1. Proyecto-ServidorWeb-master

**Ruta:** `../Proyecto-ServidorWeb-master/`

**Descripción:** Servidor web HTTP/1.0 que sirve archivos estáticos (HTML, imágenes, PDF, MP3, MP4) desde el directorio `./Server`. Usa un pool de 3 hilos para manejar conexiones concurrentes.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Servidor` | Escucha en puerto 7777. Usa `ExecutorService` con pool de 3 hilos |
| `PoolThread` | `Runnable` que recibe un socket y delega a `Peticion` |
| `Peticion` | Procesa cada petición HTTP. Parsea método, archivo y parámetros |
| `Cabecera` | Parsea cabeceras HTTP (método, archivo, parámetros GET/POST) |
| `ListMimes` | Mapa de extensiones a tipos MIME |

**Métodos soportados:**
- `GET` — Sirve archivos o muestra parámetros en HTML
- `POST` — Similar a GET, muestra parámetros POST
- `HEAD` — Solo devuelve cabeceras

**Tipos MIME:** doc, pdf, rar, mp3, jpg, jpeg, png, html, htm, mp4, java, c, txt, json

**Página por defecto:** `/prueba.json` cuando se solicita `/`

**Compilación y ejecución:**

```bash
# Compilar
javac src/*.java

# Ejecutar
java -cp src Servidor
```

---

### 2. ServidorWeb

**Ruta:** `../ServidorWeb/`

**Descripción:** Servidor web HTTP/1.0 con pool de 100 hilos para alta concurrencia. Soporta GET, POST, HEAD y DELETE. Incluye página 404 personalizada.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Servidor` | Escucha en puerto 7000. Usa `ExecutorService` con pool de 100 hilos |
| `Metodos` | Procesa cada petición HTTP. Implementa GET, POST, HEAD, DELETE |

**Métodos soportados:**
- `GET` — Sirve archivos o muestra parámetros en HTML
- `POST` — Similar a GET, muestra parámetros POST
- `HEAD` — Solo devuelve cabeceras (200 o 404)
- `DELETE` — Elimina archivos (200 si existe, 404 si no, 403 si es directorio)

**Tipos MIME:** jpg, htm, html, pdf, mp3

**Página por defecto:** `/index.htm` cuando se solicita `/`

**Compilación y ejecución:**

```bash
# Compilar
mvn compile

# Ejecutar
mvn exec:java -Dexec.mainClass="Servidor"
```

---

### 3. HtmlSocketsNoBlock

**Ruta:** `../HtmlSocketsNoBlock/`

**Descripción:** Servidor HTTP no bloqueante usando Java NIO. Un solo hilo maneja múltiples conexiones mediante un `Selector`. Soporta GET, POST, HEAD y DELETE.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `ServerHTTP` | Servidor en puerto 9000. Usa `ServerSocketChannel` no bloqueante y `Selector` |
| `Requests` | Procesa solicitudes HTTP. Parsea método, extrae parámetros, genera respuestas |
| `Mime` | Mapea extensiones a tipos MIME (html, jpg, png, mp3, pdf, etc.) |

**Características NIO:**
- `ServerSocketChannel` en modo no bloqueante (`configureBlocking(false)`)
- `Selector` para manejar eventos `OP_ACCEPT` y `OP_READ`
- `FileChannel` para transferencia eficiente de archivos

**Compilación y ejecución:**

```bash
# Compilar
mvn compile

# Ejecutar
mvn exec:java -Dexec.mainClass="ServerHTTP"
```

---

## Comparación de Servidores

| Característica | Proyecto-ServidorWeb-master | ServidorWeb | HtmlSocketsNoBlock |
|----------------|----------------------------|-------------|-------------------|
| Puerto | 7777 | 7000 | 9000 |
| Concurrencia | Pool de 3 hilos | Pool de 100 hilos | NIO (1 hilo) |
| GET | Sí | Sí | Sí |
| POST | Sí | Sí | Sí |
| HEAD | Sí | Sí | Sí |
| DELETE | No | Sí | Sí |
| Página 404 | No | Sí (personalizada) | Sí (en carpeta error/) |
| Tecnología | Sockets tradicionales | Sockets tradicionales | Java NIO |

---

## Resumen de Puertos

| Proyecto | Puerto |
|----------|--------|
| Proyecto-ServidorWeb-master | 7777 |
| ServidorWeb | 7000 |
| HtmlSocketsNoBlock | 9000 |
