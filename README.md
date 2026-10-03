# Aplicaciones para Comunicaciones en Red

Repositorio con las prácticas realizadas durante la materia de **Aplicaciones para Comunicaciones en Red** en **ESCOM**.

**Autor:** Uziel-Leizu

---

## Tabla de Contenidos

- [Descripción General](#descripción-general)
- [Estructura del Repositorio](#estructura-del-repositorio)
- [Prácticas](#prácticas)
  - [Práctica 1 - Sockets y Transferencia de Archivos](#práctica-1---sockets-y-transferencia-de-archivos)
  - [Práctica 2 - Aplicaciones con Sockets](#práctica-2---aplicaciones-con-sockets)
  - [Práctica 3 - Servidores Web](#práctica-3---servidores-web)
  - [Práctica 4 - Chat y Comunicación Grupal](#práctica-4---chat-y-comunicación-grupal)
  - [Práctica 5 - Juegos Cliente-Servidor](#práctica-5---juegos-cliente-servidor)
  - [Práctica 6 - Almacenamiento en la Nube](#práctica-6---almacenamiento-en-la-nube)
- [Herramientas y Librerías](#herramientas-y-librerías)
- [Requisitos](#requisitos)
- [Licencia](#licencia)

---

## Descripción General

Este repositorio contiene una colección de proyectos desarrollados en **Java** que cubren diversos fundamentos de las comunicaciones en red:

- **Sockets TCP y UDP** — Comunicación orientada a conexión y sin conexión
- **Protocolo HTTP** — Servidores web implementados desde cero
- **Programación multihilo** — Manejo de múltiples clientes concurrentes
- **Java NIO** — E/S no bloqueante con Selectores
- **UDP Multicast** — Comunicación grupal eficiente
- **Serialización de objetos** — Transferencia de datos estructurados
- **Transferencia de archivos** — Con protocolos personalizados y control de flujo

---

## Estructura del Repositorio

```
Aplicaciones-para-comunicaciones-en-red/
├── README.md
├── docs/
│   ├── practica1-sockets.md
│   ├── practica2-aplicaciones-sockets.md
│   ├── practica3-servidores-web.md
│   ├── practica4-chat.md
│   ├── practica5-juegos.md
│   ├── practica6-almacenamiento-nube.md
│   └── herramientas.md
├── Practica1Redes/
├── Practica 1/
├── FlujoArchivo/
├── DatagramasHola/
├── DatagramaECO/
├── Practica2-TiendaSockets/
├── TiendaCanciones_Practica2/
├── PracticaTiendaMusica/
├── NewTiendaMusica/
├── TiendaDeDulces/
├── Proyecto-ServidorWeb-master/
├── ServidorWeb/
├── HtmlSocketsNoBlock/
├── Practica 3/
├── PracticaChat/
├── Prueba chat/
├── Ahorcado/
├── ServidorAhorcado/
├── TareaAhorcado/
├── Practica1-Dropbox/
├── P1AppGestorNube/
├── Practica1 prueba/
├── Hilos/
├── mp3agic/
└── Librerias/
```

---

## Prácticas

### Práctica 1 - Sockets y Transferencia de Archivos

> **Conceptos:** Sockets TCP/UDP, datagramas, fragmentación de mensajes, flujos de datos

| Proyecto | Descripción | Protocolo |
|----------|-------------|-----------|
| [Practica1Redes](Practica1Redes/) | Esqueleto base para servidor de transferencia de archivos | TCP |
| [Practica 1](Practica%201/) | Servidor STA (Servidor de Transferencia de Archivos) - base | TCP |
| [FlujoArchivo](FlujoArchivo/) | Transferencia de archivos cliente-servidor con progreso | TCP |
| [DatagramasHola](DatagramasHola/) | Aplicación de saludo simple con UDP | UDP |
| [DatagramaECO](DatagramaECO/) | Sistema de eco UDP con fragmentación y metadatos | UDP |

**Documentación detallada:** [docs/practica1-sockets.md](docs/practica1-sockets.md)

---

### Práctica 2 - Aplicaciones con Sockets

> **Conceptos:** Aplicaciones cliente-servidor, serialización, protocolos de transferencia confiables, interfaces gráficas

| Proyecto | Descripción | Protocolo |
|----------|-------------|-----------|
| [Practica2-TiendaSockets](Practica2-TiendaSockets/) | Tienda en línea con catálogo y carrito de compras | TCP |
| [TiendaCanciones_Practica2](TiendaCanciones_Practica2/) | Tienda de música "ESCOMFY" con protocolo ACK sobre UDP | UDP |
| [PracticaTiendaMusica](PracticaTiendaMusica/) | Tienda de música "No Spotify" con reproducción de audio | UDP |
| [NewTiendaMusica](NewTiendaMusica/) | Tienda de música con descarga de MP3 | UDP |
| [TiendaDeDulces](TiendaDeDulces/) | Esqueleto de tienda (sin implementar) | — |

**Documentación detallada:** [docs/practica2-aplicaciones-sockets.md](docs/practica2-aplicaciones-sockets.md)

---

### Práctica 3 - Servidores Web

> **Conceptos:** Protocolo HTTP/1.0, pool de hilos, E/S no bloqueante (NIO), tipos MIME

| Proyecto | Descripción | Puerto |
|----------|-------------|--------|
| [Proyecto-ServidorWeb-master](Proyecto-ServidorWeb-master/) | Servidor HTTP con pool de 3 hilos | 7777 |
| [ServidorWeb](ServidorWeb/) | Servidor HTTP con pool de 100 hilos, GET/POST/HEAD/DELETE | 7000 |
| [HtmlSocketsNoBlock](HtmlSocketsNoBlock/) | Servidor HTTP NIO no bloqueante con Selector | 9000 |

**Documentación detallada:** [docs/practica3-servidores-web.md](docs/practica3-servidores-web.md)

---

### Práctica 4 - Chat y Comunicación Grupal

> **Conceptos:** Multicast UDP, patrón Observer, mensajes privados, transferencia de archivos en chat

| Proyecto | Descripción | Protocolo |
|----------|-------------|-----------|
| [Practica 3](Practica%203/) | Chat multiusuario con patrón Observer | TCP |
| [PracticaChat](PracticaChat/) | Chat grupal con multicast y mensajes privados | TCP + UDP Multicast |
| [Prueba chat](Prueba%20chat/) | Chat con emojis, archivos y renderizado HTML | UDP Multicast |

**Documentación detallada:** [docs/practica4-chat.md](docs/practica4-chat.md)

---

### Práctica 5 - Juegos Cliente-Servidor

> **Conceptos:** Lógica de juego distribuida, protocolos de texto, hilos por conexión

| Proyecto | Descripción | Protocolo |
|----------|-------------|-----------|
| [Ahorcado](Ahorcado/) | Cliente del juego del Ahorcado con interfaz gráfica | TCP |
| [ServidorAhorcado](ServidorAhorcado/) | Servidor del Ahorcado con diccionario de palabras | TCP |
| [TareaAhorcado](TareaAhorcado/) | Juego completo del Ahorcado cliente-servidor | TCP |

**Documentación detallada:** [docs/practica5-juegos.md](docs/practica5-juegos.md)

---

### Práctica 6 - Almacenamiento en la Nube

> **Conceptos:** Sincronización de archivos, árboles de directorios, compresión ZIP, interfaces con JTree

| Proyecto | Descripción | Protocolo |
|----------|-------------|-----------|
| [Practica1-Dropbox](Practica1-Dropbox/) | Gestor de archivos estilo Dropbox | TCP |
| [P1AppGestorNube](P1AppGestorNube/) | Gestor de archivos en la nube con tablas | TCP |
| [Practica1 prueba](Practica1%20prueba/) | Almacenamiento con JTree y compresión ZIP | TCP |

**Documentación detallada:** [docs/practica6-almacenamiento-nube.md](docs/practica6-almacenamiento-nube.md)

---

## Herramientas y Librerías

| Proyecto | Descripción |
|----------|-------------|
| [Hilos](Hilos/) | Servidor multihilo base (un hilo por conexión) |
| [mp3agic](mp3agic/) | Reproductor MP3 con librería de metadatos ID3 |
| [Librerias](Librerias/) | Dependencias JAR externas |

**Documentación detallada:** [docs/herramientas.md](docs/herramientas.md)

---

## Requisitos

- **Java JDK** 17 o superior
- **Apache Maven** 3.6+ (para proyectos con `pom.xml`)
- **Apache Ant** (para proyectos con `build.xml`)
- **NetBeans IDE** (opcional, para proyectos con `nbproject/`)

---

## Licencia

Este repositorio es de uso académico. Todos los derechos reservados.
