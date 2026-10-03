# Práctica 4 - Chat y Comunicación Grupal

## Objetivo

Implementar aplicaciones de chat grupal utilizando TCP, UDP multicast y patrones de diseño para la comunicación en tiempo real.

## Conceptos Cubiertos

- **UDP Multicast** — Comunicación grupal eficiente (un mensaje, múltiples receptores)
- **Patrón Observer** — Notificación de eventos a múltiples vistas
- **Mensajes privados** — Enrutamiento selectivo dentro del grupo
- **Serialización de objetos** — Transferencia de mensajes estructurados
- **Transferencia de archivos en chat** — Fragmentación y reensamblado
- **Renderizado HTML** — Uso de `JEditorPane` con `HTMLEditorKit` para emojis

---

## Proyectos

### 1. Practica 3 (chatMultiple)

**Ruta:** `../Practica 3/`

**Descripción:** Chat multiusuario estilo IRC. El servidor acepta múltiples clientes y los mensajes de cualquier cliente se muestran en el servidor. Usa el patrón Observer para notificar a las vistas.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Servidor` | Escucha en puerto 6000. Crea un `Asistente` por cliente. Extiende `Observable` |
| `Asistente` | `Runnable` que maneja la comunicación con un cliente específico |
| `Cliente` | Se conecta al servidor y envía mensajes con `DataOutputStream` |
| `ServidorView` | Vista del servidor con historial de mensajes (Observer) |
| `ClienteView` | Vista del cliente con campo de nombre y botones |
| `PrincipalView` | Menú para iniciar como Servidor o Cliente |

**Características:**
- Un hilo por cliente (`Asistente` implementa `Runnable`)
- Patrón Observer: el servidor notifica a `ServidorView` sobre nuevos mensajes
- Comando `cerrar` para desconexión

**Compilación y ejecución:**

```bash
# Compilar
mvn compile

# Ejecutar aplicación principal
mvn exec:java -Dexec.mainClass="Application.Application"
```

---

### 2. PracticaChat

**Ruta:** `../PracticaChat/`

**Descripción:** Chat grupal que usa TCP para cliente-servidor y UDP multicast para distribuir mensajes a todos los clientes. Soporta mensajes privados con el prefijo `/p`.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Servidor` | Escucha en puerto 9930 (TCP). Crea un `Manejador` por cliente. Reenvía mensajes por multicast |
| `Manejador` | Hilo que maneja cada conexión TCP. Envía mensajes al grupo multicast |
| `Cliente` | Se conecta al servidor TCP y escucha multicast en `230.1.1.1:9931` |
| `ClienteInterfaz` | Interfaz Swing con campo de nombre, área de mensajes y botón de envío |

**Protocolo de mensajes:**
- Mensajes de texto plano
- Comando `SALIR` para desconexión
- Prefijo `/p` para mensajes privados

**Grupo multicast:** `230.1.1.1` en puertos 9930 (TCP) y 9931/9932 (UDP)

**Compilación y ejecución:**

```bash
# Compilar
mvn compile

# Ejecutar servidor
mvn exec:java -Dexec.mainClass="App.Servidor"

# Ejecutar cliente
mvn exec:java -Dexec.mainClass="App.ClienteInterfaz"
```

---

### 3. Prueba chat

**Ruta:** `../Prueba chat/`

**Descripción:** Chat grupal avanzado con soporte para mensajes públicos/privados, emojis (imágenes HTML), transferencia de archivos y lista de usuarios conectados.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Principal` | Ventana inicial que pide nombre de usuario y conecta al grupo multicast |
| `Cliente` | Núcleo del cliente. Maneja conexión multicast, envío/recepción de mensajes y archivos |
| `Interfaz` | Interfaz con `JEditorPane` (HTML), área de mensaje, 12 botones de emoji, combo de usuarios |
| `Mensaje` | Objeto `Serializable` con campos para mensaje, usuario, tipo, archivo, etc. |
| `Emoji` | Define 12 emojis Unicode |

**Protocolo de tipos de mensaje:**

| Tipo | Descripción |
|------|-------------|
| 0 | Saludo/conexión (nuevo usuario) |
| 1 | Mensaje público |
| 2 | Envío de archivo |
| 3 | Confirmación de conexión |
| 4 | Mensaje privado |
| 5 | Emoji público |
| 6 | Emoji privado |

**Transferencia de archivos:**
- Archivos divididos en paquetes de 4000 bytes
- Numeración de paquete (`np`)
- Paquete 0 indica fin de transferencia

**Grupo multicast:** `230.1.1.1:9000`

**Compilación y ejecución:**

```bash
# Compilar
javac *.java

# Ejecutar
java Principal
```

---

## Resumen de Puertos y Grupos Multicast

| Proyecto | Protocolo | Puerto(s) | Grupo Multicast |
|----------|-----------|-----------|-----------------|
| Practica 3 (chatMultiple) | TCP | 6000 | — |
| PracticaChat | TCP + UDP Multicast | 9930, 9931, 9932 | 230.1.1.1 |
| Prueba chat | UDP Multicast | 9000 | 230.1.1.1 |
