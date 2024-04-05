# ahora tengo que hacer la evolución de la densidad en el tiempo
# tengo 3 casos, con 3 configuraciones distintas
# siempre se mantienen igual la L = 20.000000 , eta = 3.000000 , epocs = 300
# cambio los N para que cambie la densidad: N=200,rho=0.5; N=1000,rho=2.5; N=3600,rho=9.0

from matplotlib import pyplot as plt
import numpy as np

def read_density_file(name):
  with open(name) as archivo:
    list_y = []
    for linea in archivo:
      list_y.append(float(linea))
  return list_y

# directorio de archivos
densities = [0.5, 2.5, 9.0]
densities = [format(density, '.6f') for density in densities]
# se llaman asi SS-TP2/va_output_NX_L20.000000_eta3.000000_epocs300.txt

files = ["./SS-TP2/va_output_N200_L20.000000_eta3.000000_epocs300.txt",
          "./SS-TP2/va_output_N1000_L20.000000_eta3.000000_epocs300.txt",
          "./SS-TP2/va_output_N3600_L20.000000_eta3.000000_epocs300.txt"]

# graficar
plt.xlabel('Tiempo')
plt.ylabel('Polarizacion')

# calcular el valor máximo de densidad
max_density = 0
for i in range(len(densities)):
  density = read_density_file(files[i])
  max_density = max(max_density, max(density))

# establecer el límite superior del eje Y en función del valor máximo de densidad
plt.ylim([0.0, max_density * 1.1])

# graficar la primera configuración
for i in range(len(densities)):
  density = read_density_file(files[i])
  plt.plot(density, label="ρ = " + densities[i])

plt.legend(loc='upper right')
plt.savefig("./density_time_results.png")