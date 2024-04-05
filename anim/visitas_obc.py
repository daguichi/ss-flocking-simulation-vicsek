import sys

from matplotlib import pyplot as plt


def leer_archivo(path):
    datos_por_tiempo_corregidos = []


    with open(path, 'r') as file:
        lineas = file.readlines()
        particulas = []
        for linea in lineas:
            if linea == lineas[0]:
                continue
            if linea.startswith('t'):
                if linea == 't0\n':
                    continue
                else:
                    datos_por_tiempo_corregidos.append(particulas.copy())
                    particulas.clear()
            partes = linea.split()
            if len(partes) >= 2:
                particulas.append((float(partes[0]), float(partes[1])))
    return datos_por_tiempo_corregidos

def esta_dentro_zona_conteo(x, y, centro_x, centro_y, radio_conteo):
    """Determina si una partícula está dentro de la zona de conteo."""
    return (x - centro_x)**2 + (y - centro_y)**2 <= radio_conteo**2

# Inicialización de variables
centro_x, centro_y = 2.5, 2.5
radio_conteo = 0.5
visitas_por_tiempo = []
particulas_en_zona_conteo = set()

datos_por_tiempo_corregidos = leer_archivo(sys.argv[1])
for t, datos in enumerate(datos_por_tiempo_corregidos):
    visitas = 0
    for particula in datos:
        x, y = particula
        if esta_dentro_zona_conteo(x, y, centro_x, centro_y, radio_conteo):
            visitas += 1
    visitas_por_tiempo.append(visitas)

total_particulas = 300

# Generar el gráfico con la corrección
plt.figure(figsize=(14, 7))
plt.plot(visitas_por_tiempo, linestyle='-')

plt.xlabel('Tiempo (dt)')
plt.ylabel('Nro de visitas')
plt.xticks(range(0, len(visitas_por_tiempo), 50))  # Mostrar etiqueta cada 50 tiempos
plt.legend()

# Mostrar el gráfico
plt.show()