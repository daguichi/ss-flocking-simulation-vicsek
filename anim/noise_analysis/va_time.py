# ahora quiero hacer un grafico de Polarizacion vs Tiempo (epocs) con 3 funciones (una para cada configuracion)
# usando las mismas corridas igual que va_noise.py

# epocs = 300 as ticks
# quiero usar la carpeta de N300_L5.000000_etaX_epocs300
# y solamente usar 5 archivos de etas distintos (0.1, 2, 4, 6, 8)

# el eje x tiene los epocs y el eje y tiene la polarizacion
# el grafico va a ser del estilo plot, con una linea para cada eta
# el grafico se va a guardar en ./va_time_results.png

from matplotlib import pyplot as plt
import numpy as np

def read_vas_file(name):
  with open(name) as archivo:
    list_y = []
    for linea in archivo:
      list_y.append(float(linea))

  return list_y

etas = [0.1, 0.5, 2, 4, 6, 8]
etas = [format(eta, '.6f') for eta in etas]
folder_name = "./SS-TP2/N300_L5.000000_etaX_epocs300/"
etas_files = [folder_name + "va_output_N300_L5.000000_eta" + str(eta) + "_epocs300.txt" for eta in etas]

plt.xlabel('Tiempo')
plt.ylabel('Polarización')
plt.ylim([0.0, 1.1])

for i in range(len(etas)):
  va = read_vas_file(etas_files[i])
  plt.plot(va, label="η = " + etas[i])

plt.legend(loc='upper right')
plt.savefig("./va_time_results.png")
