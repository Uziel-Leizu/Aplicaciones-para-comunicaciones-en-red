# Práctica 2 - Aplicaciones con Sockets

## Objetivo

Desarrollar aplicaciones cliente-servidor completas con interfaces gráficas, utilizando sockets TCP/UDP y protocolos de transferencia confiables.

## Conceptos Cubiertos

- **Interfaces gráficas con Swing** — JFrame, JTable, ImageIcon, JFileChooser
- **Serialización de objetos Java** — Transferencia de objetos vía `ObjectOutputStream`
- **Protocolos de transferencia confiables** — ACK, números de secuencia, retransmisión
- **Transferencia de archivos por fragmentos** — Control de flujo con `Thread.sleep()`
- **Aplicaciones de negocio** — Tiendas en línea con catálogo, carrito y tickets

---

## Proyectos

### 1. Practica2-TiendaSockets

**Ruta:** `../Practica2-TiendaSockets/`

**Descripción:** Tienda en línea estilo Amazon con 6 productos relacionados con tomate. Permite ver catálogo, agregar al carrito y comprar (actualiza stock en el servidor).

**Clases principales:**

| Clase | Función |
|-------|---------|
| `TienditaSocketsServer` | Servidor en puerto 25001. Envía catálogo de 6 productos, recibe orden y actualiza stock |
| `TienditaSocketsCliente` | Cliente de consola que recibe y muestra el catálogo |
| `ClienteUI` | Interfaz gráfica con productos, imágenes, precios, stock y botones de comprar |
| `CarritoUI` | Ventana del carrito con resumen, descuentos y total |
| `Articulos` | POJO con nombre, precio, stock, descuento, imagen |

**Compilación y ejecución:**

```bash
# Compilar
ant compile

# Ejecutar servidor
ant run -Dmain.class=TienditaSocketsServer

# Ejecutar cliente UI
ant run -Dmain.class=ClienteUI
```

---

### 2. TiendaCanciones_Practica2

**Ruta:** `../TiendaCanciones_Practica2/`

**Descripción:** Tienda de música "ESCOMFY" con 13 canciones (Bad Bunny, Billie Eilish, Camilo, Banda MS, Grupo Firme). Implementa un protocolo de transferencia confiable sobre UDP con ACKs y números de secuencia.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Servidor` | Escucha en puerto 4040 (UDP). Envía archivos MP3 con protocolo ACK |
| `FrmCliente` | Interfaz principal con catálogo, imágenes de álbum y navegación |
| `FrmServidor` | Interfaz del servidor con botón "Mandar catalogo" |
| `Carrito` | Carrito de compras con tabla y finalización de compra |
| `Cancion` | Objeto `Serializable` con id, nombre, precio, año, duración, artista, imagen |
| `GestionCancion` | Genera el catálogo de 13 canciones |
| `Ticket` | Recibo de compra con lista y total |

**Protocolo de transferencia confiable:**

- Números de secuencia de 2 bytes (big-endian)
- Bandera de fin de archivo (1 byte)
- ACK de 2 bytes por cada paquete recibido
- Timeout de 50ms para retransmisión
- Tamaño de paquete: 1024 bytes (3 bytes header + 1021 bytes datos)

**Compilación y ejecución:**

```bash
# Compilar
mvn compile

# Ejecutar servidor
mvn exec:java -Dexec.mainClass="Clases.FrmServidor"

# Ejecutar cliente
mvn exec:java -Dexec.mainClass="Clases.FrmCliente"
```

---

### 3. PracticaTiendaMusica

**Ruta:** `../PracticaTiendaMusica/`

**Descripción:** Tienda de música "No Spotify" que permite comprar canciones. El servidor UDP envía el catálogo y luego transfiere los archivos MP3 en fragmentos de 32000 bytes.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Servidor` | Escucha en puerto 9000 (UDP). Envía catálogo y archivos MP3 fragmentados |
| `ClienteUI` | Interfaz principal con reproductor (Play, Stop, Siguiente, Atrás) |
| `Carrito` | Carrito de compras que recibe archivos por UDP |

**Características:**
- Reproducción de audio con Java Sound API (`Clip`, `AudioInputStream`)
- Transferencia de MP3 en fragmentos de 32000 bytes con `Thread.sleep(5)` entre envíos

**Compilación y ejecución:**

```bash
# Compilar
mvn compile

# Ejecutar servidor
mvn exec:java -Dexec.mainClass="App.Servidor"

# Ejecutar cliente
mvn exec:java -Dexec.mainClass="App.ClienteUI"
```

---

### 4. NewTiendaMusica

**Ruta:** `../NewTiendaMusica/`

**Descripción:** Tienda de música estilo Spotify/NoSpotify con catálogo de 6 canciones. Permite ver canciones con nombre, artista, año, precio e imagen, agregar al carrito y comprar (descarga MP3).

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Servidor` | Escucha en puerto 9000 (UDP). Envía objetos `Cancion` serializados y archivos MP3 |
| `ClienteUID` | Interfaz principal con navegación (anterior/siguiente) y carrito |
| `Carrito` | Ventana del carrito con tabla y finalización de compra |
| `Ticket` | Resumen de compra con total |
| `Cancion` | POJO `Serializable` con id, nombre, precio, año, artista, imagen |
| `GenCan` | Generador del catálogo hardcodeado |

**Compilación y ejecución:**

```bash
# Compilar
mvn compile

# Ejecutar servidor
mvn exec:java -Dexec.mainClass="App.Servidor"

# Ejecutar cliente
mvn exec:java -Dexec.mainClass="App.ClienteUID"
```

---

### 5. TiendaDeDulces

**Ruta:** `../TiendaDeDulces/`

**Descripción:** Proyecto en estado inicial (esqueleto vacío). El archivo `Servidor.java` solo contiene un `main` sin implementación.

**Estado:** Sin implementar

---

## Resumen de Puertos

| Proyecto | Protocolo | Puerto |
|----------|-----------|--------|
| Practica2-TiendaSockets | TCP | 25001 |
| TiendaCanciones_Practica2 (catálogo) | UDP | 9090 |
| TiendaCanciones_Practica2 (archivos) | UDP | 4040 |
| PracticaTiendaMusica | UDP | 9000 |
| NewTiendaMusica | UDP | 9000 |
