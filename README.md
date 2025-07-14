# 🐦 Simulación de Bandadas - Modelo Vicsek

## 📋 Descripción

Este proyecto implementa una simulación de **bandadas de agentes autopropulsados** basada en el modelo Vicsek, desarrollado para la materia **Simulación de Sistemas** del ITBA. La simulación modela el comportamiento colectivo de partículas que se mueven en un espacio 2D, donde cada partícula ajusta su dirección de movimiento basándose en la dirección promedio de sus vecinos más un término de ruido.

## 🛠️ Tecnologías Utilizadas

### Backend (Simulación)
- **Java 8+** - Lenguaje principal para la implementación del algoritmo
- **Maven** - Gestión de dependencias y build del proyecto

### Análisis y Visualización
- **Python 3.x** - Scripts de análisis y animación
- **NumPy** - Cálculos numéricos y manipulación de datos
- **Matplotlib** - Generación de gráficos y animaciones
- **Pillow** - Creación de archivos GIF para las animaciones

## 🎯 Características Principales

- **Simulación Off-Lattice**: Las partículas se mueven en un espacio continuo
- **Condiciones de Frontera Periódicas**: El espacio es toroidal (las partículas que salen por un lado entran por el opuesto)
- **Cálculo de Vecinos**: Implementación eficiente usando grilla para detectar partículas cercanas
- **Parámetros Configurables**:
  - Número de partículas
  - Longitud del lado del espacio (L)
  - Radio de interacción (r_c)
  - Número de épocas de simulación
  - Parámetro de ruido (η)

## 📁 Estructura del Proyecto

```
SS-TP2/
├── src/main/java/ar/edu/itba/ss/
│   ├── Main.java                 # Punto de entrada principal
│   ├── OffLatice.java           # Clase principal de simulación
│   ├── models/
│   │   ├── Cell.java            # Representación de celdas de la grilla
│   │   ├── Grid.java            # Grilla para optimización de vecinos
│   │   ├── Particle.java        # Modelo de partícula
│   │   └── Velocity.java        # Representación de velocidad
│   └── utils/
│       └── ParticleReader.java  # Lectura de archivos de entrada
├── anim/
│   ├── anim.py                  # Script principal de animación
│   ├── density_analysis/        # Análisis de densidad
│   ├── noise_analysis/          # Análisis de ruido
│   ├── visitas_obc.py          # Visualización con condiciones abiertas
│   └── visitas_pbc.py          # Visualización con condiciones periódicas
├── static.txt                   # Archivo de configuración estática
├── dynamic.txt                  # Archivo de configuración dinámica
└── output.txt                   # Resultados de la simulación
```

## 🚀 Uso

### Compilación y Ejecución

```bash
# Compilar el proyecto Java
javac -d bin src/main/java/ar/edu/itba/ss/*.java src/main/java/ar/edu/itba/ss/models/*.java src/main/java/ar/edu/itba/ss/utils/*.java

# Ejecutar la simulación
java -cp bin ar.edu.itba.ss.Main static.txt dynamic.txt [true/false]
```

### Generación de Animaciones

```bash
# Generar animación desde los resultados
python3 anim/anim.py output.txt
```

## 📊 Formato de Archivos

### Archivo Estático (static.txt)
```
N        # Número de partículas
L        # Longitud del lado del espacio
M        # Número de celdas por lado (sqrt)
r_c      # Radio de interacción
epocs    # Número de épocas
eta      # Parámetro de ruido
```

### Archivo Dinámico (dynamic.txt)
```
t0
x1 y1 speed1 angle1
x2 y2 speed2 angle2
...
t1
...
```

## 🎨 Visualización

El proyecto incluye scripts de Python para:
- **Animaciones**: Visualización del movimiento de las partículas con vectores de velocidad coloreados según el ángulo
- **Análisis de Densidad**: Estudio del comportamiento del sistema según la densidad de partículas
- **Análisis de Ruido**: Investigación del efecto del parámetro de ruido en la formación de bandadas

## 📈 Resultados

La simulación genera:
- **Archivos de salida**: Posiciones y velocidades de todas las partículas en cada época
- **Archivos VA**: Valores de velocidad promedio del sistema en cada iteración
- **Animaciones GIF**: Visualización dinámica del comportamiento colectivo

## 🔬 Aplicaciones

Este tipo de simulación tiene aplicaciones en:
- **Biología**: Comportamiento de bandadas de pájaros, bancos de peces
- **Robótica**: Coordinación de robots autónomos
- **Física**: Sistemas de partículas activas
- **Ciencias Sociales**: Modelado de comportamiento colectivo humano

## 👨‍💻 Autor

Desarrollado como trabajo práctico para la materia **Simulación de Sistemas** del Instituto Tecnológico de Buenos Aires (ITBA).

---

*Este proyecto demuestra la implementación de algoritmos de simulación de sistemas complejos y la visualización de fenómenos emergentes en sistemas de partículas autopropulsadas.* 