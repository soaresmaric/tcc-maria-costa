import matplotlib
matplotlib.use('Agg')  # Use o backend 'Agg' para evitar problemas com GTK

import pandas as pd
import matplotlib.pyplot as plt
from pandas.plotting import table

# Criando o DataFrame com os dados estatísticos
data_statistics = {
    'Test': ['Teste 1', 'Teste 2', 'Teste 3'],
    'Mean (s)': [27.50, 25.21, 22.53],
    'Median (s)': [25.3, 25.7, 26.1],
    'Mode (s)': [25.2, 25.7, 26.2],
    'Std Dev (s)': [12.90, 13.89, 7.46],
    'Range (s)': [44.1, 45.0, 15.2]
}

df_statistics = pd.DataFrame(data_statistics)

# Configurando a figura
plt.figure(figsize=(8, 4))

# Ocultando o eixo principal
ax = plt.subplot(111, frame_on=False)
ax.xaxis.set_visible(False)
ax.yaxis.set_visible(False)

# Criando a tabela a partir do DataFrame
tbl = table(ax, df_statistics, loc='center', cellLoc='center', colWidths=[0.1]*len(df_statistics.columns))

# Salvando a tabela em um arquivo PNG
plt.savefig("startup_statistics_table.png", bbox_inches='tight')

plt.close()  # Fechar a figura para liberar recursos