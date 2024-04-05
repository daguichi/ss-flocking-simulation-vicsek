# hacer gráfico de va en funcion de la densidad

from matplotlib import pyplot as plt
import numpy as np

# voy a tener densidades 0.25, 0.5, 1, 2.5, 4, 6, 9
# los archivos se llaman va_output_NX_L20.000000_eta3.000000_epocs300.txt

rhos = [0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5, 6, 7, 9]
# n_particles = [200, 400, 1000, 1600, 2400, 3600]
n_particles = [int(rho * 400) for rho in rhos]
# print(n_particles)
# for rho, n_particle in zip(rhos, n_particles):
#   print(rho, n_particle)
# files array de strings usando el array n_particles y un for n in n_particles
files = ["./SS-TP2/va_output_N" + str(n) + "_L20.000000_eta3.000000_epocs300.txt" for n in n_particles]

def read_vas_file(name):
  with open(name) as archivo:
    list_y = []
    for linea in archivo:
      list_y.append(float(linea))

    # solo considerar las últimas 150 líneas
    list_y = list_y[-150:]

  return np.mean(list_y), np.std(list_y)
# cada leida de file va a ser un valor para el eje x (valor en rho[i] con el errorbar creado por read_vas_file(files[i]))
plt.xlabel('Densidad')
plt.ylabel('Polarización')

for i in range(len(rhos)):
  mean, std = read_vas_file(files[i])
  plt.errorbar(rhos[i], mean, yerr=std, fmt="o", color='blue')


plt.xticks(range(int(min(rhos)), int(max(rhos)) + 1))
plt.savefig("./va_density_results.png")