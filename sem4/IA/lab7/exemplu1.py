import warnings;

from sklearn import linear_model
from sklearn.metrics import mean_squared_error

warnings.simplefilter('ignore')
import csv
import matplotlib.pyplot as plt
import numpy as np


def loadData(fileName, inputVariabName, outputVariabName):
    data = []
    dataNames = []
    with open(fileName) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                dataNames = row
            else:
                data.append(row)
            line_count += 1
    selectedVariable = dataNames.index(inputVariabName)
    inputs = [float(data[i][selectedVariable]) for i in range(len(data))]
    selectedOutput = dataNames.index(outputVariabName)
    outputs = [float(data[i][selectedOutput]) for i in range(len(data))]

    return inputs, outputs


def plotDataHistogram(x, variableName):
    n, bins, patches = plt.hist(x, 10)
    plt.title('Histogram of ' + variableName)
    plt.show()


def plotData(x1, y1, x2 = None, y2 = None, x3 = None, y3 = None, title = None):
    plt.plot(x1, y1, 'ro', label = 'train data')
    if (x2):
        plt.plot(x2, y2, 'b-', label = 'learnt model')
    if (x3):
        plt.plot(x3, y3, 'g^', label = 'test data')
    plt.title(title)
    plt.legend()
    plt.show()


def univariateRegression():
    import os

    crtDir =  os.getcwd()
    filePath = os.path.join(crtDir, 'data', 'world-happiness-report-2017.csv')

    inputs, outputs = loadData(filePath, 'Economy..GDP.per.Capita.', 'Happiness.Score')

    plotDataHistogram(inputs, 'capita GDP')
    plotDataHistogram(outputs, 'Happiness score')

    # check the liniarity (to check that a linear relationship exists between the dependent variable (y = happiness) and the independent variable (x = capita).)
    plotData(inputs, outputs, [], [], [], [], 'capita vs. hapiness')

    # split data into training data (80%) and testing data (20%)
    np.random.seed(5)
    indexes = [i for i in range(len(inputs))]
    trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace = False)
    testSample = [i for i in indexes  if not i in trainSample]
    trainInputs = [inputs[i] for i in trainSample]
    trainOutputs = [outputs[i] for i in trainSample]
    testInputs = [inputs[i] for i in testSample]
    testOutputs = [outputs[i] for i in testSample]

    plotData(trainInputs, trainOutputs, [], [], testInputs, testOutputs, "train and test data")

    # training step
    xx = [[el] for el in trainInputs]
    regressor = linear_model.SGDRegressor(max_iter =  10000)
    # regressor = MySGDRegression()
    regressor.fit(xx, trainOutputs)
    w0, w1 = regressor.intercept_, regressor.coef_[0]

    # plot the model
    noOfPoints = 1000
    xref = []
    val = min(trainInputs)
    step = (max(trainInputs) - min(trainInputs)) / noOfPoints
    for i in range(1, noOfPoints):
        xref.append(val)
        val += step
    yref = [w0 + w1 * el for el in xref]
    plotData(trainInputs, trainOutputs, xref, yref, [], [], title="train data and model")

    # makes predictions for test data
    # computedTestOutputs = [w0 + w1 * el for el in testInputs]
    # makes predictions for test data (by tool)
    computedTestOutputs = regressor.predict([[x] for x in testInputs])
    plotData([], [], testInputs, computedTestOutputs, testInputs, testOutputs, "predictions vs real test data")

    # compute the differences between the predictions and real outputs
    error = 0.0
    for t1, t2 in zip(computedTestOutputs, testOutputs):
        error += (t1 - t2) ** 2
    error = error / len(testOutputs)
    print("prediction error (manual): ", error)

    error = mean_squared_error(testOutputs, computedTestOutputs)
    print("prediction error (tool): ", error)


univariateRegression()


# # using developed code
# from SGD import MySGDRegression
# # model initialisation
# regressor = MySGDRegression()
#
# regressor.fit(trainInputs, trainOutputs)
#
# #parameters of the liniar regressor
# w0, w1, w2 = regressor.intercept_, regressor.coef_[0], regressor.coef_[1]
# print('the learnt model: f(x) = ', w0, ' + ', w1, ' * x1 + ', w2, ' * x2' )
#
# #numerical representation of the regressor model
# noOfPoints = 50
# xref1 = []
# val = min(feature1)
# step1 = (max(feature1) - min(feature1)) / noOfPoints
# for _ in range(1, noOfPoints):
#     for _ in range(1, noOfPoints):
#         xref1.append(val)
#     val += step1
#
# xref2 = []
# val = min(feature2)
# step2 = (max(feature2) - min(feature2)) / noOfPoints
# for _ in range(1, noOfPoints):
#     aux = val
#     for _ in range(1, noOfPoints):
#         xref2.append(aux)
#         aux += step2
# yref = [w0 + w1 * el1 + w2 * el2 for el1, el2 in zip(xref1, xref2)]
# plot3Ddata(feature1train, feature2train, trainOutputs, xref1, xref2, yref, [], [], [], 'train data and the learnt model')
#
# # makes predictions for test data
# # computedTestOutputs = [w0 + w1 * el[0] + w2 * el[1] for el in testInputs]
# # makes predictions for test data (by tool)
# computedTestOutputs = regressor.predict(testInputs)
#
# plot3Ddata([], [], [], feature1test, feature2test, computedTestOutputs, feature1test, feature2test, testOutputs, 'predictions vs real test data')
#
# #compute the differences between the predictions and real outputs
# error = 0.0
# for t1, t2 in zip(computedTestOutputs, testOutputs):
#     error += (t1 - t2) ** 2
# error = error / len(testOutputs)
# print('prediction error (manual): ', error)
#
# from sklearn.metrics import mean_squared_error
#
# error = mean_squared_error(testOutputs, computedTestOutputs)
# print('prediction error (tool):   ', error)