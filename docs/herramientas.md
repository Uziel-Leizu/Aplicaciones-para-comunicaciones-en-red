# Herramientas y Librerías

## Proyectos de Soporte

### 1. Hilos

**Ruta:** `../Hilos/`

**Descripción:** Servidor TCP multihilo base. Acepta múltiples conexiones simultáneas, cada una manejada en un hilo separado. Es un esqueleto para implementar lógica de negocio.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Servidor` | Servidor TCP en puerto 5555. Bucle infinito que acepta conexiones y lanza un `Manejator` por cliente |
| `Manejator` | Clase que extiende `Thread`. Recibe un `Socket` y sobrescribe `run()` (actualmente vacío) |

**Patrón:** Un hilo por conexión para manejo concurrente

**Compilación y ejecución:**

```bash
# Compilar
mvn compile

# Ejecutar
mvn exec:java -Dexec.mainClass="App.Servidor"
```

---

### 2. mp3agic

**Ruta:** `../mp3agic/`

**Descripción:** Reproductor MP3 con interfaz gráfica que muestra metadatos (ID3v1/ID3v2), carátula del álbum y controles de reproducción. Incluye la librería mp3agic completa para manipulación de archivos MP3.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Mp3GUI` | Ventana principal con botones para seleccionar, reproducir, pausar y detener. Muestra carátula y metadatos |
| `Ejemplo` | Programa de consola que demuestra el uso de la librería mp3agic |
| `Mp3File` | Clase central de la librería. Representa un archivo MP3, escanea frames MPEG, gestiona tags |
| `AbstractID3v2Tag` | Clase abstracta base para tags ID3v2. Define constantes para todos los frames |
| `ID3v1Tag` | Implementación de tags ID3v1 (formato antiguo de 128 bytes) |
| `ID3v24Tag`, `ID3v23Tag`, `ID3v22Tag` | Implementaciones específicas de versiones ID3v2 |
| `MpegFrame` | Representa un frame MPEG de audio |
| `BufferTools`, `ByteBufferUtils` | Utilidades para manipulación de bytes |
| `EncodedText` | Manejo de codificación de texto en tags ID3 |

**Patrones de diseño:**
- **Factory:** `ID3v2TagFactory` crea tags según la versión
- **Template Method:** `AbstractID3v2Tag` define la estructura, las subclases implementan detalles
- **NIO File I/O:** Uso de `SeekableByteChannel` y `ByteBuffer` para lectura eficiente

**Dependencias:**
- `basicplayer3.0.jar` — Reproducción de audio
- `jl1.0.1.jar`, `mp3spi1.9.4.jar`, `tritonus_share.jar` — Soporte de audio

**Compilación y ejecución:**

```bash
# Compilar
javac -cp "lib/*" src/*.java src/mp3agic/*.java

# Ejecutar
java -cp "lib/*;src" Mp3GUI
```

---

### 3. Librerias

**Ruta:** `../Librerias/`

**Descripción:** Contiene las librerías JAR externas utilizadas por los proyectos.

**Contenido:**

| Archivo | Descripción |
|---------|-------------|
| `jid3-0.46.jar` | Librería para manipulación de tags ID3 en archivos MP3 |

---

## Resumen

| Proyecto | Tipo | Lenguaje |
|----------|------|----------|
| Hilos | Servidor multihilo base | Java |
| mp3agic | Reproductor MP3 + librería | Java |
| Librerias | Dependencias JAR | Java |
