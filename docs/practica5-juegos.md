# Práctica 5 - Juegos Cliente-Servidor

## Objetivo

Implementar un juego del Ahorcado cliente-servidor, distribuyendo la lógica del juego entre cliente y servidor mediante sockets TCP.

## Conceptos Cubiertos

- **Protocolos de texto** — Comunicación mediante líneas de texto plano
- **Lógica distribuida** — El servidor controla el estado del juego
- **Hilos por conexión** — Múltiples clientes simultáneos
- **Interfaces gráficas** — Swing con botones para cada letra del abecedario

---

## Proyectos

### 1. Ahorcado (Cliente)

**Ruta:** `../Ahorcado/`

**Descripción:** Cliente del juego del Ahorcado con interfaz gráfica. Se conecta al servidor en `localhost:10578` para obtener una palabra secreta y juega adivinando letras.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Ahorcado` | Lógica del juego. Gestiona palabra secreta, intentos, validación de letras y conexión TCP |
| `Principal` | Ventana principal (JFrame) con botones para cada letra, campo de palabra oculta e imágenes |
| `main` | Punto de entrada que lanza la ventana Principal |

**Características:**
- Botones para cada letra del abecedario
- `KeyListener` para capturar teclas presionadas
- Imágenes de ahorcado que cambian según errores
- Separación MVC: lógica (`logica/`) separada de vista (`vista/`)

**Compilación y ejecución:**

```bash
# Compilar
ant compile

# Ejecutar
ant run -Dmain.class=logica.main
```

---

### 2. ServidorAhorcado

**Ruta:** `../ServidorAhorcado/`

**Descripción:** Servidor del juego del Ahorcado. Selecciona una palabra aleatoria de un diccionario y la envía al cliente. Cada conexión se maneja en un hilo separado.

**Clases principales:**

| Clase | Función |
|-------|---------|
| `main` | Punto de entrada. Escucha en puerto 10578. Crea un hilo `Servidor` por conexión |
| `Servidor` | Hilo que selecciona palabra aleatoria del diccionario y la envía al cliente |

**Diccionario:** 100+ palabras hardcodeadas en el código

**Nota:** El servidor está incompleto. El método `run()` está vacío y no hay lógica de juego (no verifica letras, no cuenta errores, no determina ganador/perdedor).

**Compilación y ejecución:**

```bash
# Compilar
ant compile

# Ejecutar
ant run -Dmain.class=servidorahorcado.main
```

---

### 3. TareaAhorcado

**Ruta:** `../TareaAhorcado/`

**Descripción:** Juego completo del Ahorcado cliente-servidor. El servidor selecciona una palabra aleatoria y el cliente debe adivinarla letra por letra. El servidor lleva el contador de errores (máximo 6).

**Clases principales:**

| Clase | Función |
|-------|---------|
| `Server` | Escucha en puerto 45000. Selecciona palabra aleatoria, envía estado con guiones, recibe letras |
| `Cliente` | Se conecta al servidor. Muestra estado de la palabra, pide letras por consola |

**Lógica del juego en el servidor:**
- `inicializarpalabra()`: Crea string de guiones del tamaño de la palabra
- `actualizarPalabra()`: Revela letras correctas, incrementa contador de errores si falla
- Máximo 6 errores antes de enviar `FIN`

**Palabras disponibles:** holamundo, ornitorrinco, buscaminas, cazafantasmas, sandia, criptomonedas, molecula, teclado, estudiante

**Protocolo:**
- Servidor envía palabra con guiones (`---`)
- Cliente envía una letra
- Servidor responde con palabra actualizada o `FIN` si perdió

**Compilación y ejecución:**

```bash
# Compilar
mvn compile

# Ejecutar servidor
mvn exec:java -Dexec.mainClass="Server"

# Ejecutar cliente
mvn exec:java -Dexec.mainClass="Cliente"
```

---

## Resumen de Puertos

| Proyecto | Protocolo | Puerto |
|----------|-----------|--------|
| Ahorcado (cliente) | TCP | 10578 |
| ServidorAhorcado | TCP | 10578 |
| TareaAhorcado | TCP | 45000 |
