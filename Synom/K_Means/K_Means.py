import numpy as np 
from sklearn.cluster import KMeans

f= open("DataSet.txt","r")

dataSet = f.read()
f.close()

#print(dataSet)

# initialize an array of strings with each elem null
dispersions = ["" for x in range(dataSet.count("\n"))]

#print(len(dispersions)-1)

# each value of the array will be a dispersion
j=0
for i in range(0, len(dispersions)-1):
    while (dataSet[j]!='\n'):
        dispersions[i] += dataSet[j]
        j = j + 1
    if(dataSet[j]=='\n'):
        j = j + 1

# remove the last new line character
dispersions.remove(dispersions[len(dispersions)-1])

# convert array of strings to array of double
dispersions = np.array(dispersions)

# reshape dispersion as a column vector
dispersions = dispersions.reshape(-1, 1)

# initialize centroids to be used for the kmeans
centroids = np.array([[0], [100]]) 

# compute kmeans with 2 clusters
kmeans = KMeans(n_clusters=2, init='random', n_init=100, max_iter=300)

# train the network
kmeans.fit(dispersions)

process = np.array([150])
process = process.reshape(-1,1)
print(kmeans.labels_)
print(kmeans.n_iter_)
print(kmeans.predict(process))
