import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_csv("data.csv", delimiter=" ")
data = data.set_index("InputSize")

data[["RandomBSTInsert", "RandomAVLInsert"]].plot(linewidth=4)

plt.savefig("random_insert.png")

data[["OrderedBSTInsert", "OrderedAVLInsert"]].plot(linewidth=4)

plt.savefig("ordered_insert.png")

data[["RandomBSTSearch", "RandomAVLSearch"]].plot(linewidth=4)

plt.savefig("random_search.png")

data[["OrderedBSTSearch", "OrderedAVLSearch"]].plot(linewidth=4)

plt.savefig("ordered_search.png")

data[["RandomBSTNegativeSearch", "RandomAVLNegativeSearch"]].plot(linewidth=4)

plt.savefig("random_negative_search.png")

data[["OrderedBSTNegativeSearch", "OrderedAVLNegativeSearch"]].plot(linewidth=4)

plt.savefig("ordered_negative_search.png")