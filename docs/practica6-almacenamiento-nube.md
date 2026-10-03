# Práctica 6 - Almacenamiento en la Nube

## Objetivo

Implementar aplicaciones de almacenamiento en la nube (estilo Dropbox/Google Drive) con sincronización de archivos entre cliente y servidor.

## Conceptos Cubiertos

- **Sincronización de archivos** — Intercambio de listas de archivos entre cliente y servidor
- **Transferencia de archivos** — Con `DataInputStream`/`DataOutputStream`
- **Serialización de objetos** — Envío de árboles de directorios con `JTree`
- **Compresión ZIP** — Compresión recursiva de carpetas
- **Interfaces con JTree** — Visualización de árboles de directorios
- **Sockets separados** — Nueva conexión para cada operación

---

## Proyectos

### 1. Practica1-Dropbox

**Ruta:** `../Practica1-Dropbox/`

**Descripción:** Gestor de archivos estilo Dropbox. Permite ver archivos del cliente y del servidor en tablas, subir y descargar archivos.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Servidor` | Escucha en puerto 25001. Intercambia listas de archivos. Soporta "Subir" y "Bajar" |
| `Cliente` | Cliente de consola que establece conexión, intercambia listas, permite subir archivos |
| `ClienteUI` | Interfaz gráfica con dos tablas (Cliente/Servidor), botones para subir/descargar/limpiar/actualizar |

**Protocolo de comunicación:**
- Intercambio de directorios con formato `Dir: nombre{salto}` / `File: nombre{salto}`
- Transferencia de archivos con `DataInputStream`/`DataOutputStream`
- Sockets separados para transferencia de archivos

**Compilación y ejecución:**

```bash
# Compilar
ant compile

# Ejecutar servidor
ant run -Dmain.class=Servidor

# Ejecutar cliente UI
ant run -Dmain.class=ClienteUI
```

---

### 2. P1AppGestorNube

**Ruta:** `../P1AppGestorNube/`

**Descripción:** Gestor de archivos en la nube con interfaz gráfica. Permite ver archivos del cliente y del servidor en tablas separadas, subir y descargar archivos.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Servidor` | Escucha en puerto 5555. Recibe lista de archivos del cliente, envía su propia lista |
| `Cliente` | Interfaz gráfica con dos tablas (Cliente/Servidor). Permite subir/descargar archivos |

**Características:**
- Intercambio de listas de archivos entre cliente y servidor
- Operaciones "Subir" (recibe archivos) y "Bajar" (envía archivos)
- `ListSelectionListener` para selección de archivos

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

### 3. Practica1 prueba

**Ruta:** `../Practica1 prueba/`

**Descripción:** Sistema de almacenamiento en la nube con árbol de archivos (JTree). Permite ver el árbol del servidor, subir archivos/carpetas (comprimidas como ZIP), crear carpetas, eliminar y descargar.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Server` | Servidor en puerto 1239. Métodos: sendDirectory, receiveAction, uploadFile, getFile, zipDir |
| `Drive` | Main del servidor. Construye JTree del directorio "DriveFiles", lo envía al cliente |
| `Client` | Cliente que recibe el JTree, envía acciones, sube/descarga archivos con compresión ZIP |
| `UserInterface` | Interfaz con JTree, botones para subir/crear/borrar/descargar |
| `Directory` | Wrapper `Serializable` que contiene un JTree para transferir el árbol |
| `Comprimir` | Utilidad para comprimir directorios recursivamente |

**Protocolo de acciones:**

| Código | Acción |
|--------|--------|
| 1 | Subir archivo/carpeta |
| 2 | Crear carpeta |
| 3 | Borrar archivo/carpeta |
| 4 | Descargar archivo |
| 5 | Salir |

**Características:**
- Serialización de objetos: se envía un JTree serializado dentro de un objeto `Directory`
- Compresión ZIP recursiva para carpetas
- Reconstrucción del JTree en cada operación
- Sockets múltiples: nueva conexión para cada operación

**Compilación y ejecución:**

```bash
# Compilar
javac -cp . Servidor/*.java Cliente/*.java

# Ejecutar servidor
java Servidor.Drive

# Ejecutar cliente
java Cliente.Client
```

---

## Resumen de Puertos

| Proyecto | Protocolo | Puerto |
|----------|-----------|--------|
| Practica1-Dropbox | TCP | 25001 |
| P1AppGestorNube | TCP | 5555 |
| Practica1 prueba | TCP | 1239 |
