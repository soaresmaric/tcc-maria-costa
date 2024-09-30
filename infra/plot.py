import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt


#import matplotlib.pyplot as plt

# Dados de exemplo extraídos da imagem fornecida
times = ["22:30:00", "22:35:00", "22:40:00", "22:45:00", "22:50:00"]
cpu_usage = [99, 118, 163, 75, 50 ]  # Substitua por valores reais do gráfico

# Criando o gráfico
plt.figure(figsize=(10, 6))
plt.plot(times, cpu_usage, marker='o')

# Adicionando títulos e labels
plt.title('Application Uptime Over Time')
plt.xlabel('Time')
plt.ylabel('Restart time (ms)')
plt.grid(True)

# Salvando o gráfico como PNG
plt.savefig('cpu_usage_over_time.png')

# Exibindo o gráfico
plt.show()
