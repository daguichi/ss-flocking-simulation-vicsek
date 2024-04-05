# 2 Ejercicio: Autómata Off-Lattice: Bandadas de agentes autopropulsados
# - Implementar el algoritmo de bandadas descripto en la teórica [1]. Para cada variable de estudio ( η y ρ) presentar:
# a) Animaciones: A partir de las posiciones y velocidades generadas por las simulaciones hacer animaciones que muestren la dinámica del sistema. Cada agente será representado por un vector (velocidad) cuyo origen estará ubicado en la posición de la partícula para cada tiempo de simulación t.
# - Repetir las animaciones anteriores pero cambiando el color (o la escala de grises) de los vectores según el ángulo de la velocidad.
# usar ../output.txt
# tiene este estilo
# t0
# 3.9835525 2.3386947 0.03 5.4008928
# 1.2239251 4.3910902 0.03 2.3234507
# 11.185408 14.547373 0.03 3.238649
# 12.154552 4.7688251 0.03 0.55999551
# 14.365128 9.8726384 0.03 5.1630598
# 11.702894 14.741985 0.03 1.0942455
# 15.784801 5.8343245 0.03 2.0350825
# 19.306713 4.2336929 0.03 6.1112859
# x y speed angle
# ....
# t1
# ...

import numpy as np
import sys

input = "./" + sys.argv[1]
particles_per_t = []
# whenever a new t is found, a new list is appended to particles_per_t
# and the particles are appended to that list
# you may read particles line by line, starting with t0 and when t1 appears, a new list is appended
# and so on
with open(input, 'r') as file:
    particles = []
    #la primera linea es L, quiero que sea un int
    L = float(file.readline().strip())
    # luego seguir con las demas lineas

    for line in file:
        #leyendo linea tal
        # print(line)
        if line.startswith('t'):
            if particles:
                particles_per_t.append(particles)
                particles = []
        else:
            x, y, speed, angle = map(float, line.strip().split())
            particles.append((x, y, angle))
    particles_per_t.append(particles)  # append the last particles

# checkeo, imprimir la cantidad de particulas en cada t
for i, particles in enumerate(particles_per_t):
    print(f"t{i}: {len(particles)}")

# ahora que tenemos las particulas por t, podemos hacer la animacion
import matplotlib.pyplot as plt
import matplotlib.animation as animation
import matplotlib.colors as mcolors

def angle_to_color(angle):
    color_map = mcolors.LinearSegmentedColormap.from_list('angle_cmap', ['red', 'orange', 'yellow', 'green', 'cyan', 'blue', 'purple'])
    normalized_angle = angle / (2 * np.pi)
    return color_map(normalized_angle)

def update_plot(frame):
    plt.clf()
    plt.title(f"Iteration {frame+1}")

    for x, y, angle in particles_per_t[frame]:
        arrow_length = 0.1
        dx = arrow_length * np.cos(angle)
        dy = arrow_length * np.sin(angle)
        color = angle_to_color(angle)
        plt.arrow(x, y, dx, dy, head_width=0.05, head_length=0.05, fc=color, ec=color)

    plt.xlim(0, L)
    plt.ylim(0, L)
    plt.xticks([])  # Removes tick labels on the x-axis
    plt.yticks([])


fig = plt.figure()
plt.xlabel(None)
plt.ylabel(None)
plt.xticks([])  # Removes tick labels on the x-axis
plt.yticks([])  # Removes tick labels on the y-axis
# darle mas segundos, 2 segundos por frame
ani = animation.FuncAnimation(fig, update_plot, frames=len(particles_per_t), interval=16.67 ,repeat=True)
# save the animation as a gif

ani.save("./results/animations/" + input.replace(".txt", ".gif"), writer='pillow', fps=60, dpi=300)
plt.show()
