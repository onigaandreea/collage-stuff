from sklearn.datasets import load_breast_cancer
from sklearn.preprocessing import StandardScaler

# step1: load data (breast cancer & 2 features), plot pt distributia datelor

data = load_breast_cancer()
print(data)
inputs = data['data']
outputs = data['target']
outputNames = data['target_names']
featureNames = list(data['feature_names'])
feature1 = [feat[featureNames.index('mean radius')] for feat in inputs]
feature2 = [feat[featureNames.index('mean texture')] for feat in inputs]
inputs = [[feat[featureNames.index('mean radius')], feat[featureNames.index('mean texture')]] for feat in inputs]

import matplotlib.pyplot as plt
labels = set(outputs)
noData = len(inputs)
for crtLabel in labels:
    x = [feature1[i] for i in range(noData) if outputs[i] == crtLabel ]
    y = [feature2[i] for i in range(noData) if outputs[i] == crtLabel ]
    plt.scatter(x, y, label = outputNames[crtLabel])
plt.xlabel('mean radius')
plt.ylabel('mean texture')
plt.legend()
plt.show()

fig, ax = plt.subplots(1, 3,  figsize=(4 * 3, 4))
ax[0].hist(feature1, 10)
ax[0].title.set_text('Histogram of mean radius')
ax[1].hist(feature2, 10)
ax[1].title.set_text('Histogram of mean texture')
ax[2].hist(outputs, 10)
ax[2].title.set_text('Histogram of cancer class')
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

def plotClassificationData(feature1, feature2, outputs, title = None):
    labels = set(outputs)
    noData = len(feature1)
    for crtLabel in labels:
        x = [feature1[i] for i in range(noData) if outputs[i] == crtLabel ]
        y = [feature2[i] for i in range(noData) if outputs[i] == crtLabel ]
        plt.scatter(x, y, label = outputNames[crtLabel])
    plt.xlabel('mean radius')
    plt.ylabel('mean texture')
    plt.legend()
    plt.title(title)
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
feature1test = [ex[0] for ex in testInputs]
feature2test = [ex[1] for ex in testInputs]

plotClassificationData(feature1train, feature2train, trainOutputs, 'normalised train data')

# step3: invatare model (cu tool linear_model.LogisticRegression() -- [link](https://scikit-learn.org/stable/modules/generated/sklearn.linear_model.LogisticRegression.html) -- si cu implementare proprie)


#identify (by training) the classifier

# # using sklearn
# from sklearn import linear_model
# classifier = linear_model.LogisticRegression()

# using developed code
from LogisticRegression import MyLogisticRegression
# model initialisation
classifier = MyLogisticRegression()

# train the classifier (fit in on the training data)
classifier.fit(trainInputs, trainOutputs)
# parameters of the liniar regressor
w0, w1, w2 = classifier.intercept_, classifier.coef_[0], classifier.coef_[1]
print('classification model: y(feat1, feat2) = ', w0, ' + ', w1, ' * feat1 + ', w2, ' * feat2')

# step4: testare model, plot rezultate, forma outputului si interpretarea lui

# makes predictions for test data
# computedTestOutputs = [w0 + w1 * el[0] + w2 * el[1] for el in testInputs]

# makes predictions for test data (by tool)
computedTestOutputs = classifier.predict(testInputs)

def plotPredictions(feature1, feature2, realOutputs, computedOutputs, title, labelNames):
    labels = list(set(outputs))
    noData = len(feature1)
    for crtLabel in labels:
        x = [feature1[i] for i in range(noData) if realOutputs[i] == crtLabel and computedOutputs[i] == crtLabel ]
        y = [feature2[i] for i in range(noData) if realOutputs[i] == crtLabel and computedOutputs[i] == crtLabel]
        plt.scatter(x, y, label = labelNames[crtLabel] + ' (correct)')
    for crtLabel in labels:
        x = [feature1[i] for i in range(noData) if realOutputs[i] == crtLabel and computedOutputs[i] != crtLabel ]
        y = [feature2[i] for i in range(noData) if realOutputs[i] == crtLabel and computedOutputs[i] != crtLabel]
        plt.scatter(x, y, label = labelNames[crtLabel] + ' (incorrect)')
    plt.xlabel('mean radius')
    plt.ylabel('mean texture')
    plt.legend()
    plt.title(title)
    plt.show()

plotPredictions(feature1test, feature2test, testOutputs, computedTestOutputs, "real test data", outputNames)

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