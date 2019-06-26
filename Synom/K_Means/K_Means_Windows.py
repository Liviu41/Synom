import numpy as np 
from sklearn.cluster import KMeans

f= open("/home/liviu/Synom/Synom/Data_Sets/DataSet_Artificial_Windows.txt","r")

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
# training completed



f= open("/home/liviu/Synom/Synom/Data_Sets/toBeClustered.txt","r")

toBeClustered = f.read()
f.close()

print(toBeClustered)

pids = ["" for x in range(toBeClustered.count("\n"))]
disp = ["" for x in range(toBeClustered.count("\n"))]

# get the array of pids
j=0
for i in range(0, len(pids)-1):
    while (toBeClustered[j]!=' '):
        pids[i] += toBeClustered[j]
        j = j + 1
    if(toBeClustered[j]==' '):
        while (toBeClustered[j]!='\n'):
            j = j + 1
    j = j + 1

# remove the last new line character
pids.remove(pids[len(pids)-1])


#get the array of disps
j=0
for i in range(0, len(disp)-1):
    while (toBeClustered[j] != ' '):
        j = j + 1
    if(toBeClustered[j]==' '):
        j = j + 1
        while (toBeClustered[j] != '\n'):
            disp[i] += toBeClustered[j]
            j = j + 1
    j = j + 1

# remove the last new line character
disp.remove(disp[len(disp)-1])

disp = np.array(disp)

# reshape dispersion as a column vector
disp = disp.reshape(-1, 1)


labels = kmeans.labels_

predict = kmeans.predict(disp)

if(labels[1] == 0):
    switch = 0
else:
    switch = 1

cnt = 0
arr = ["" for x in range(toBeClustered.count("\n"))]
for i in range(0, len(predict)):
   if(predict[i] != switch):
       arr[cnt] = i
       cnt = cnt + 1
      
print(predict)
        
numberOfOcc = arr.count('')

for i in range(numberOfOcc):
    arr.remove('')

print(arr)

toBeKilled = ''

for i in range(0, len(arr)):
    toBeKilled = toBeKilled + pids[arr[i]] + "\n"

print(toBeKilled)

g = open("/home/liviu/Synom/Synom/Data_Sets/toBeKilled.txt","w+")
g.write(toBeKilled)
g.close()
       
    
    
    
    
    
    
    
    
    







