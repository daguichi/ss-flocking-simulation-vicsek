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
    for indice, particula in enumerate(datos, start=1):  # Comenzamos el índice en 1
        x, y = particula
        if esta_dentro_zona_conteo(x, y, centro_x, centro_y, radio_conteo):
            if indice not in particulas_en_zona_conteo:
                visitas += 1
                particulas_en_zona_conteo.add(indice)
    visitas_por_tiempo.append(visitas)

total_particulas = 300
# Calcular visitas acumuladas
visitas_acumuladas = [sum(visitas_por_tiempo[:i+1]) for i in range(len(visitas_por_tiempo))]
# Calcular el porcentaje de visitas acumuladas respecto al total de partículas
porcentaje_visitas_acumuladas = [visitas / total_particulas * 100 for visitas in visitas_acumuladas]




# Generar el gráfico con la corrección
plt.figure(figsize=(14, 7))
plt.plot(visitas_acumuladas, label='Visitas Acumuladas', linestyle='-')
plt.plot(porcentaje_visitas_acumuladas, label='Porcentaje de Visitas Acumuladas (%)', linestyle='--', color='red')

plt.xlabel('Tiempo (dt)')
plt.ylabel('Visitas Acumuladas / Porcentaje (%)')
plt.xticks(range(0, len(visitas_por_tiempo), 50))  # Mostrar etiqueta cada 5 tiempos
plt.legend()

# Mostrar el gráfico
plt.show()

