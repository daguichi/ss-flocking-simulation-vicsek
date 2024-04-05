# hacer un grafico de va en funcion del ruido
from matplotlib import pyplot as plt
import numpy as np

# tengo archivos del estilo va_output_N4000_L28.284200_eta0.330000_epocs1000.txt en donde el eta va cambiando, 
# quiero que me hagas un grafico Polarizacion (Va) vs Ruido
# los archivos estan en ../../
# cada linea representa la velocidad va (que es polarizacion)
# el grafico va a ser del estilo errorbar, con el promedio y la desviacion estandar
# el ruido va a ser el valor de eta
# el ruido va a estar en el eje x, y va en el eje y
# el grafico se va a guardar en ./va_noise_results.png

# los incrementos son de 0.5 pero el primero es 0.1, 0.5 y recien ahi incrementa hasta 8
# 0.1, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5, 5.5, 6, 6.5, 7, 7.5, 8


# metodo para leer archivo y dar el mean, el std 
def read_vas_file(name):
  with open(name) as archivo:
    list_y = []
    for linea in archivo:
      list_y.append(float(linea))

  list_y = list_y[-250:]
  return np.mean(list_y), np.std(list_y)
  
# directorio de archivos
etas = [0.1, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5, 5.5, 6, 6.5, 7, 7.5, 8]
etas = [format(eta, '.6f') for eta in etas]

# mapear los etas a los nombres de archivos para la primera configuración
etas_files1 = ["./SS-TP2/N300_L5.000000_etaX_epocs300/va_output_N300_L5.000000_eta" + str(eta) + "_epocs300.txt" for eta in etas]

# mapear los etas a los nombres de archivos para la segunda configuración
etas_files2 = ["./SS-TP2/N1000_L9.128710_etaX_epocs300/va_output_N1000_L9.128710_eta" + str(eta) + "_epocs300.txt" for eta in etas]

# mapear los etas a los nombres de archivos para la tercera configuración
etas_files3 = ["./SS-TP2/N40_L1.825741_etaX_epocs300/va_output_N40_L1.825741_eta" + str(eta) + "_epocs300.txt" for eta in etas]


# graficar
plt.xlabel('Ruido')
plt.ylabel('Polarización')

# graficar la primera configuración
for i in range(len(etas)):
  mean, std = read_vas_file(etas_files1[i])
  plt.errorbar(float(etas[i]), mean, yerr=std, fmt="o", color='blue', label='N=300, L=5.000000' if i == 0 else "")


# graficar la segunda configuración
for i in range(len(etas)):
  mean, std = read_vas_file(etas_files2[i])
  plt.errorbar(float(etas[i]), mean, yerr=std, fmt="o", color='red', label='N=1000, L=9.128710' if i == 0 else "")


# graficar la tercera configuración
for i in range(len(etas)):
  mean, std = read_vas_file(etas_files3[i])
  plt.errorbar(float(etas[i]), mean, yerr=std, fmt="o", color='green', label='N=40, L=1.825741' if i == 0 else "")

#plot y limit 0 a 1
plt.ylim([0.0, 1.1])
# mostrar la leyenda
plt.legend()

plt.savefig("./va_noise_results.png")