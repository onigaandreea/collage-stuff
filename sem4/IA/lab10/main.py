import csv
import os
import numpy as np
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics import accuracy_score
from gensim.models import Word2Vec

# Functii utile
def readCsv(filename):
    data = []
    with open(filename) as f:
        reader = csv.DictReader(f, delimiter=',')
        for row in reader:
            data.append(row)
    return data


def mapLabelToValue(label):
    if label == 'positive':
        return 0
    return 1

# Clustering cu K-Means
crtDir = os.getcwd()
fileName = os.path.join(crtDir, 'data', 'reviews_mixed.csv')

data = readCsv(fileName)
inputs = [x['Text'] for x in data]
outputs = [mapLabelToValue(x['Sentiment']) for x in data]
labelNames = ['positive', 'negative']

# Clustering cu K-Means
vectorizer = TfidfVectorizer(max_features=50)
features = vectorizer.fit_transform(inputs).toarray()

from kmeans import KMeans
kmeans = KMeans(n_clusters=2)
kmeans.fit(features)

clusterIndexes = kmeans.predict(features)
clusterLabels = [labelNames[value] for value in clusterIndexes]

print("Cluster Labels: ", clusterLabels[:10])

# Extragere caracteristici din texte - Bag of Words / TF-IDF / Word2Vec
vectorizer = TfidfVectorizer(max_features=50)
bowFeatures = vectorizer.fit_transform(inputs).toarray()

word2vecModel = Word2Vec([text.split() for text in inputs], min_count=1)
word2vecFeatures = np.array([np.mean([word2vecModel.wv[word] for word in text.split()], axis=0) for text in inputs])

# Alte caracteristici - de exemplu, lungimea textului
textLengths = np.array([len(text) for text in inputs])

# Etichetare emotii - nesupervizat
emotionLabels = clusterLabels
print(emotionLabels)

# Etichetare emotii - supervizat
trainInputs, testInputs, trainOutputs, testOutputs = train_test_split(inputs, outputs, test_size=0.2)

vectorizer = TfidfVectorizer(max_features=50)
trainFeatures = vectorizer.fit_transform(trainInputs).toarray()
testFeatures = vectorizer.transform(testInputs).toarray()

classifier = LogisticRegression()
classifier.fit(trainFeatures, trainOutputs)
computedTestOutputs = classifier.predict(testFeatures)

print("Supervised Emotion Classification Accuracy: ", accuracy_score(testOutputs, computedTestOutputs))

# Etichetare emotii - hibrid
hybridFeatures = np.concatenate((bowFeatures, np.expand_dims(textLengths, axis=1)), axis=1)

