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

# los incrementos son de 0.33 entre 0 y 8 
#(0.33, 0.66, 1, 1.33, 1.66, 2, 2.33, 2.66, 3, 3.33, 3.66, 4, 4.33, 4.66, 5, 5.33, 5.66, 6, 6.33, 6.66, 7, 7.33, 7.66, 8)
# leer los archivos va_output_N4000_L28.284200_eta0.330000_epocs1000.txt ...

# metodo para leer archivo y dar el mean, el std 
def read_vas_file(name):
  with open(name) as archivo:
    list_y = []
    for linea in archivo:
      list_y.append(float(linea))

  return np.mean(list_y), np.std(list_y)
  
# directorio de archivos
etas = [0.33, 0.66, 1, 1.33, 1.66, 2, 2.33, 2.66, 3, 3.33, 3.66, 4, 4.33, 4.66, 5, 5.33, 5.66, 6, 6.33, 6.66, 7, 7.33, 7.66, 8]
etas = [format(eta, '.6f') for eta in etas]
# mapear los etas a los nombres de archivos
etas_files = ["./SS-TP2/va_output_N4000_L28.284200_eta" + str(eta) + "_epocs1000.txt" for eta in etas]

#imprimir etas_files
print(etas_files)

# graficar
plt.xlabel('Ruido')
plt.ylabel('Polarización')
label = False
for i in range(len(etas)):
  mean, std = read_vas_file(etas_files[i])
  plt.errorbar(etas[i], mean, yerr=std, fmt="o")

plt.savefig("./va_noise_results.png")
