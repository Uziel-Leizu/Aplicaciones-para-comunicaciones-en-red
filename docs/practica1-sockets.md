# Práctica 1 - Sockets y Transferencia de Archivos

## Objetivo

Comprender los fundamentos de la comunicación en red mediante sockets TCP y UDP, implementando aplicaciones cliente-servidor básicas.

## Conceptos Cubiertos

- **Sockets TCP** — Comunicación orientada a conexión, confiable y ordenada
- **Sockets UDP** — Comunicación sin conexión, datagramas independientes
- **Fragmentación de mensajes** — División de datos en paquetes pequeños
- **Flujos de datos (Streams)** — Lectura/escritura con `DataInputStream`/`DataOutputStream`
- **Serialización de bytes** — Uso de `ByteArrayOutputStream` y `DataOutputStream`

---

## Proyectos

### 1. Practica1Redes

**Ruta:** `../Practica1Redes/`

**Descripción:** Esqueleto base para un servidor de transferencia de archivos. El servidor escucha en el puerto 8000 y está preparado para recibir archivos (lee nombre y tamaño), pero la lógica de guardado no está implementada.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Servidor` | Escucha en puerto 8000, acepta conexiones, lee nombre y tamaño de archivo |
| `Cliente` | Stub vacío (punto de partida) |

**Compilación y ejecución:**

```bash
# Compilar
mvn compile

# Ejecutar servidor
mvn exec:java -Dexec.mainClass="Servidor"

# Ejecutar cliente
mvn exec:java -Dexec.mainClass="Cliente"
```

---

### 2. Practica 1 (Practica1STA)

**Ruta:** `../Practica 1/`

**Descripción:** Servidor de Transferencia de Archivos (STA) en estado base. Escucha en el puerto 25001 y muestra los archivos del directorio `archivosServidor`, pero no implementa la transferencia.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Servidor` | Escucha en puerto 25001, lista archivos del servidor |
| `Cliente` | Stub vacío |

**Compilación y ejecución:**

```bash
# Compilar
mvn compile

# Ejecutar servidor
mvn exec:java -Dexec.mainClass="App.Servidor"
```

---

### 3. FlujoArchivo

**Ruta:** `../FlujoArchivo/`

**Descripción:** Transferencia de archivos cliente-servidor usando TCP. El cliente puede subir archivos al servidor, que los guarda en la carpeta `archivos/`. Incluye visualización de progreso porcentual.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `CEnvia` | Cliente TCP. Menú interactivo para subir, descargar y listar archivos. Usa `JFileChooser` |
| `SRecibe` | Servidor TCP en puerto 8000. Recibe archivos en bloques de 1500 bytes |

**Protocolo de transferencia:**

1. Cliente envía nombre del archivo (`writeUTF`)
2. Cliente envía tamaño del archivo (`writeLong`)
3. Cliente envía datos en bloques de 1500 bytes
4. Servidor muestra progreso porcentual

**Compilación y ejecución:**

```bash
# Compilar
ant compile

# Ejecutar servidor
ant run -Dmain.class=SRecibe

# Ejecutar cliente
ant run -Dmain.class=CEnvia
```

---

### 4. DatagramasHola

**Ruta:** `../DatagramasHola/`

**Descripción:** Aplicación de saludo simple usando UDP. El cliente envía un nombre al servidor y este responde con "Hola [nombre]".

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Servidor` | Servidor UDP en puerto 5555. Recibe nombre y responde con saludo |
| `Cliente` | Cliente UDP que lee nombre por consola y muestra respuesta |

**Compilación y ejecución:**

```bash
# Compilar
mvn compile

# Ejecutar servidor
mvn exec:java -Dexec.mainClass="App.Servidor"

# Ejecutar cliente
mvn exec:java -Dexec.mainClass="App.Cliente"
```

---

### 5. DatagramaECO

**Ruta:** `../DatagramaECO/`

**Descripción:** Sistema de eco (ECHO) usando datagramas UDP. Incluye fragmentación de mensajes grandes y manejo de metadatos.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `CecoD` | Cliente UDP. Envía mensajes al servidor en `127.0.0.1:4444`. Fragmenta mensajes >10 bytes |
| `SecoD` | Servidor UDP en puerto 4444. Recibe datagramas y devuelve el mensaje sin el número de paquete |
| `Emetadatos` | Cliente que envía mensajes con metadatos (número de paquete + longitud + datos) |
| `RMetadatos` | Servidor en puerto 5555 que recibe y deserializa metadatos |

**Fragmentación:**

- Mensajes menores a 10 bytes: se envían en un solo paquete
- Mensajes mayores a 10 bytes: se dividen en fragmentos numerados
- El servidor reconstruye el mensaje completo

**Compilación y ejecución:**

```bash
# Compilar
ant compile

# Ejecutar servidor de eco
ant run -Dmain.class=SecoD

# Ejecutar cliente de eco
ant run -Dmain.class=CecoD

# Ejecutar servidor de metadatos
ant run -Dmain.class=RMetadatos

# Ejecutar cliente de metadatos
ant run -Dmain.class=Emetadatos
```

---

## Resumen de Puertos

| Proyecto | Protocolo | Puerto |
|----------|-----------|--------|
| Practica1Redes | TCP | 8000 |
| Practica 1 (STA) | TCP | 25001 |
| FlujoArchivo | TCP | 8000 |
| DatagramasHola | UDP | 5555 |
| DatagramaECO (eco) | UDP | 4444 |
| DatagramaECO (metadatos) | UDP | 5555 |
