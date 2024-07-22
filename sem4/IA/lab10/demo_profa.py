import csv
import os

# load some data
crtDir =  os.getcwd()
fileName = os.path.join(crtDir, 'data', 'spam.csv')

data = []
with open(fileName) as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    for row in csv_reader:
        if line_count == 0:
            dataNames = row
        else:
            data.append(row)
        line_count += 1

inputs = [data[i][0] for i in range(len(data))]
outputs = [data[i][1] for i in range(len(data))]
labelNames = list(set(outputs))

print(inputs[:2])
print(labelNames[:2])

# prepare data for training and testing

import numpy as np

np.random.seed(5)
# noSamples = inputs.shape[0]
noSamples = len(inputs)
indexes = [i for i in range(noSamples)]
trainSample = np.random.choice(indexes, int(0.8 * noSamples), replace = False)
testSample = [i for i in indexes  if not i in trainSample]

trainInputs = [inputs[i] for i in trainSample]
trainOutputs = [outputs[i] for i in trainSample]
testInputs = [inputs[i] for i in testSample]
testOutputs = [outputs[i] for i in testSample]

print(trainInputs[:3])

# extract some features from the raw text

# # representation 1: Bag of Words
from sklearn.feature_extraction.text import CountVectorizer
vectorizer = CountVectorizer()

trainFeatures = vectorizer.fit_transform(trainInputs)
testFeatures = vectorizer.transform(testInputs)

# vocabbulary from the train data
print('vocab: ', vectorizer.get_feature_names_out()[:10])
# extracted features
print('features: ', trainFeatures.toarray()[:3][:10])

# representation 2: tf-idf features - word granularity
from sklearn.feature_extraction.text import TfidfVectorizer
vectorizer = TfidfVectorizer(max_features=50)

trainFeatures = vectorizer.fit_transform(trainInputs)
testFeatures = vectorizer.transform(testInputs)

# vocabbulary from the train data
print('vocab: ', vectorizer.get_feature_names_out()[:10])
# extracted features
print('features: ', trainFeatures.toarray()[:3])

# representation 3: embedded features extracted by a pre-train model (in fact, word2vec pretrained model)

# import gensim
#
# # Load Google's pre-trained Word2Vec
# crtDir =  os.getcwd()
# modelPath = os.path.join(crtDir, 'models', 'GoogleNews-vectors-negative300.bin')
#
# word2vecModel300 = gensim.models.KeyedVectors.load_word2vec_format(modelPath, binary=True)
# print(word2vecModel300.most_similar('support'))
# print("vec for house: ", word2vecModel300["house"])
#
# def featureComputation(model, data):
#     features = []
#     phrases = [ phrase.split() for phrase in data]
#     for phrase in phrases:
#         # compute the embeddings of all the words from a phrase (words of more than 2 characters) known by the model
#         vectors = [model[word] for word in phrase if (len(word) > 2) and (word in model.vocab.keys())]
#         if len(vectors) == 0:
#             result = [0.0] * model.vector_size
#         else:
#             result = np.sum(vectors, axis=0) / len(vectors)
#         features.append(result)
#     return features
#
# trainFeatures = featureComputation(word2vecModel300, trainInputs)
# testFeatures = featureComputation(word2vecModel300, testInputs)
#
# # unsupervised classification ( = clustering) of data

from sklearn.cluster import KMeans

unsupervisedClassifier = KMeans(n_clusters=2, random_state=0)
unsupervisedClassifier.fit(trainFeatures)

computedTestIndexes = unsupervisedClassifier.predict(testFeatures)
computedTestOutputs = [labelNames[value] for value in computedTestIndexes]

from sklearn.metrics import accuracy_score

# just supposing that we have the true labels
print("acc: ", accuracy_score(testOutputs, computedTestOutputs))