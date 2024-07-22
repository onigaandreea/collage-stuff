from sklearn.preprocessing import StandardScaler
from sklearn.datasets import load_iris

# step1: load data, plot pt distributia datelor
data = load_iris()
inputs = data['data']
outputs = data['target']
outputNames = data['target_names']
featureNames = list(data['feature_names'])
feature1 = [feat[featureNames.index('sepal length (cm)')] for feat in inputs]
feature2 = [feat[featureNames.index('sepal width (cm)')] for feat in inputs]
feature3 = [feat[featureNames.index('petal length (cm)')] for feat in inputs]
feature4 = [feat[featureNames.index('petal width (cm)')] for feat in inputs]
inputs = [[feat[featureNames.index('sepal length (cm)')], feat[featureNames.index('sepal length (cm)')], feat[featureNames.index('petal length (cm)')], feat[featureNames.index('petal width (cm)')]] for feat in inputs]

import matplotlib.pyplot as plt

labels = set(outputs)
noData = len(inputs)
fig, ax = plt.subplots(1, 4, figsize=(4 * 4, 4))
for crtLabel in labels:
    x = [feature1[i] for i in range(noData) if outputs[i] == crtLabel]
    y = [feature2[i] for i in range(noData) if outputs[i] == crtLabel]
    z = [feature3[i] for i in range(noData) if outputs[i] == crtLabel]
    w = [feature4[i] for i in range(noData) if outputs[i] == crtLabel]
    ax[0].scatter(x, y, label=outputNames[crtLabel])
    ax[1].scatter(x, z, label=outputNames[crtLabel])
    ax[2].scatter(x, w, label=outputNames[crtLabel])
    ax[3].scatter(y, z, label=outputNames[crtLabel])

ax[0].set_xlabel('sepal length (cm)')
ax[0].set_ylabel('sepal width (cm)')
ax[0].legend()
ax[1].set_xlabel('sepal length (cm)')
ax[1].set_ylabel('petal length (cm)')
ax[1].legend()
ax[2].set_xlabel('sepal length (cm)')
ax[2].set_ylabel('petal width (cm)')
ax[2].legend()
ax[3].set_xlabel('sepal width (cm)')
ax[3].set_ylabel('petal length (cm)')
ax[3].legend()

plt.show()

fig, ax = plt.subplots(2, 2, figsize=(8, 8))
ax[0, 0].hist(feature1, 10)
ax[0, 0].title.set_text('Histogram of sepal length (cm)')
ax[0, 1].hist(feature2, 10)
ax[0, 1].title.set_text('Histogram of sepal width (cm)')
ax[1, 0].hist(feature3, 10)
ax[1, 0].title.set_text('Histogram of petal length (cm)')
ax[1, 1].hist(feature4, 10)
ax[1, 1].title.set_text('Histogram of petal width (cm)')

plt.show()


def normalisation(trainData, testData):
    scaler = StandardScaler()
    if not isinstance(trainData[0], list):
        # encode each sample into a list
        trainData = [[d] for d in trainData]
        testData = [[d] for d in testData]

        scaler.fit(trainData)  # fit only on training data
        normalisedTrainData = scaler.transform(trainData)  # apply same transformation to train data
        normalisedTestData = scaler.transform(testData)  # apply same transformation to test data

        # decode from list to raw values
        normalisedTrainData = [el[0] for el in normalisedTrainData]
        normalisedTestData = [el[0] for el in normalisedTestData]
    else:
        scaler.fit(trainData)  # fit only on training data
        normalisedTrainData = scaler.transform(trainData)  # apply same transformation to train data
        normalisedTestData = scaler.transform(testData)  # apply same transformation to test data
    return normalisedTrainData, normalisedTestData


def plotClassificationData(feature1, feature2, feature3, feature4, outputs, title=None):
    labels = set(outputs)
    noData = len(feature1)
    fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(10, 4))
    for crtLabel in labels:
        x = [feature1[i] for i in range(noData) if outputs[i] == crtLabel]
        y = [feature2[i] for i in range(noData) if outputs[i] == crtLabel]
        z = [feature3[i] for i in range(noData) if outputs[i] == crtLabel]
        c = [feature4[i] for i in range(noData) if outputs[i] == crtLabel]
        ax1.scatter(x, y, c=c, label=outputNames[crtLabel])
        ax2.scatter(z, y, c=c, label=outputNames[crtLabel])
    ax1.set_xlabel('sepal length (cm)')
    ax1.set_ylabel('sepal width (cm)')
    ax2.set_xlabel('petal length (cm)')
    ax2.set_ylabel('petal width (cm)')
    ax1.legend()
    ax2.legend()
    if title:
        fig.suptitle(title)
    plt.show()


# step2: impartire pe train si test
# step2': normalizare
import numpy as np

# split data into train and test subsets
np.random.seed(5)
indexes = [i for i in range(len(inputs))]
trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace = False)
testSample = [i for i in indexes  if not i in trainSample]

trainInputs = [inputs[i] for i in trainSample]
trainOutputs = [outputs[i] for i in trainSample]
testInputs = [inputs[i] for i in testSample]
testOutputs = [outputs[i] for i in testSample]

#normalise the features
trainInputs, testInputs = normalisation(trainInputs, testInputs)

#plot the normalised data
feature1train = [ex[0] for ex in trainInputs]
feature2train = [ex[1] for ex in trainInputs]
feature3train = [ex[2] for ex in trainInputs]
feature4train = [ex[3] for ex in trainInputs]
feature1test = [ex[0] for ex in testInputs]
feature2test = [ex[1] for ex in testInputs]
feature3test = [ex[2] for ex in testInputs]
feature4test = [ex[3] for ex in testInputs]

plotClassificationData(feature1train, feature2train, feature3train, feature4train, trainOutputs, 'normalised train data')

# using sklearn
# from sklearn import linear_model
# classifier = linear_model.LogisticRegression()

# using developed code
from LogisticRegression import MyLogisticRegression
# model initialisation
classifier = MyLogisticRegression()

# train the classifier (fit in on the training data)
classifier.fit(trainInputs, trainOutputs)
# parameters of the liniar regressor
w0, w1, w2, w3, w4 = classifier.intercept_, classifier.coef_[0], classifier.coef_[1], classifier.coef_[2], classifier.coef_[3]
print('classification model: y(feat1, feat2, feat3, feat4) = ', w0, ' + ', w1, ' * feat1 + ', w2, ' * feat2 + ', w3, ' * feat3 + ', w4, ' * feat4')


# makes predictions for test data
# computedTestOutputs = [w0 + w1 * el[0] + w2 * el[1] for el in testInputs]

# makes predictions for test data (by tool)
computedTestOutputs = classifier.predict(testInputs)

def plotPredictions(feature1, feature2, feature3, feature4, realOutputs, computedOutputs, title, labelNames):
    labels = list(set(outputs))
    noData = len(feature1)
    for crtLabel in labels:
        x = [feature1[i] for i in range(noData) if realOutputs[i] == crtLabel and computedOutputs[i] == crtLabel ]
        y = [feature2[i] for i in range(noData) if realOutputs[i] == crtLabel and computedOutputs[i] == crtLabel]
        z = [feature3[i] for i in range(noData) if realOutputs[i] == crtLabel and computedOutputs[i] == crtLabel]
        w = [feature4[i] for i in range(noData) if realOutputs[i] == crtLabel and computedOutputs[i] == crtLabel]
        plt.scatter(x, y, z, w, label = labelNames[crtLabel] + ' (correct)')
    for crtLabel in labels:
        x = [feature1[i] for i in range(noData) if realOutputs[i] == crtLabel and computedOutputs[i] != crtLabel ]
        y = [feature2[i] for i in range(noData) if realOutputs[i] == crtLabel and computedOutputs[i] != crtLabel]
        z = [feature3[i] for i in range(noData) if realOutputs[i] == crtLabel and computedOutputs[i] != crtLabel]
        w = [feature4[i] for i in range(noData) if realOutputs[i] == crtLabel and computedOutputs[i] != crtLabel]
        plt.scatter(x, y, z, w, label = labelNames[crtLabel] + ' (incorrect)')
    plt.xlabel('feature1')
    plt.ylabel('feature2')
    plt.zlabel('feature3')
    plt.wlabel('feature4')
    plt.legend()
    plt.title(title)
    plt.show()

# step5: calcul metrici de performanta (acc)

# evalaute the classifier performance
# compute the differences between the predictions and real outputs
# print("acc score: ", classifier.score(testInputs, testOutputs))
error = 0.0
for t1, t2 in zip(computedTestOutputs, testOutputs):
    if (t1 != t2):
        error += 1
error = error / len(testOutputs)
print("classification error (manual): ", error)

from sklearn.metrics import accuracy_score
error = 1 - accuracy_score(testOutputs, computedTestOutputs)
print("classification error (tool): ", error)