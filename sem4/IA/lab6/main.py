import csv
import os
import matplotlib.pyplot as plt
import numpy as np
from sklearn import linear_model
from sklearn.metrics import mean_squared_error
from LinearRegression import LinearRegression


# load data and consider two features (Economy..GDP.per.capita and Health..Life.Expectancy) and the output to be estimated (happiness)
# def loadData(fileName, inputVariabNames, outputVariabName):
#     data = []
#     dataNames = []
#     with open(fileName) as csv_file:
#         csv_reader = csv.reader(csv_file, delimiter=',')
#         line_count = 0
#         for row in csv_reader:
#             if line_count == 0:
#                 dataNames = row
#             else:
#                 data.append(row)
#             line_count += 1
#     selectedVariables = [dataNames.index(inputVariabNames[i]) for i in range(len(inputVariabNames))]
#     inputs = [[float(data[i][j]) for j in selectedVariables] for i in range(len(data))]
#     selectedOutput = dataNames.index(outputVariabName)
#     outputs = [float(data[i][selectedOutput]) for i in range(len(data))]
#     return inputs, outputs

def loadData(fileName, inputVariabNames, outputVariabName, removeMissingData=True):
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
        selectedVariables = [dataNames.index(inputVariabNames[i]) for i in range(len(inputVariabNames))]
        selectedOutput = dataNames.index(outputVariabName)
        inputs, outputs = [], []
        for i in range(len(data)):
            row = data[i]
            missingData = False
            for j in selectedVariables + [selectedOutput]:
                if row[j] == "":
                    missingData = True
                    break
            if not missingData or not removeMissingData:
                inputValues = [float(row[j]) if row[j] != "" else np.nan for j in selectedVariables]
                outputValue = float(row[selectedOutput]) if row[selectedOutput] != "" else np.nan
                inputs.append(inputValues)
                outputs.append(outputValue)
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

crtDir =  os.getcwd()
filePath = os.path.join(crtDir, 'data', 'v3_world-happiness-report-2017.csv')

# load data and select two features
inputs, outputs = loadData(filePath, ['Economy..GDP.per.Capita.', 'Freedom'], 'Happiness.Score')

# check the liniarity (to check that a linear relationship exists between the dependent variable (y = happiness) and the independent variables)
plotData([x[0] for x in inputs], outputs, [], [], [], [], 'Capita GDP vs. happiness')
plotData([x[1] for x in inputs], outputs, [], [], [], [], 'Freedom vs. happiness')

# split data into training data (80%) and testing data (20%)
np.random.seed(5)
indexes = [i for i in range(len(inputs))]
trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace = False)
validationSample = [i for i in indexes  if not i in trainSample]
trainInputs = [inputs[i] for i in trainSample]
trainOutputs = [outputs[i] for i in trainSample]
validationInputs = [inputs[i] for i in validationSample]
validationOutputs = [outputs[i] for i in validationSample]

# training step
regressor = linear_model.LinearRegression()
regressor.fit(trainInputs, trainOutputs)
w0, w1, w2 = regressor.intercept_, regressor.coef_[0], regressor.coef_[1]

# plot the model
xx1 = [[el, min([x[1] for x in inputs])] for el in [min([x[0] for x in inputs]), max([x[0] for x in inputs])]]
xx2 = [[min([x[0] for x in inputs]), el] for el in [min([x[1] for x in inputs]), max([x[1] for x in inputs])]]
yref = [w0 + w1 * el[0] + w2 * el[1] for el in xx1]
plotData([x[0] for x in inputs], outputs, xx1, yref, [], [], title = "Capita GDP vs. happiness and Freedom vs. happiness")

# make predictions for test data
computedValidationOutputs = regressor.predict(validationInputs)
plotData([], [], validationOutputs, computedValidationOutputs, validationOutputs, validationOutputs, "predictions vs real test data")

error = 0.0
for t1, t2 in zip(computedValidationOutputs, validationOutputs):
    error += (t1 - t2) ** 2
error = error / len(validationOutputs)
print("prediction error (manual): ", error)

# compute the differences between the predictions and real outputs
error = mean_squared_error(validationOutputs, computedValidationOutputs)
print("prediction error: ", error)

# load data and select two features
inputs, outputs = loadData(filePath, ['Economy..GDP.per.Capita.', 'Freedom'], 'Happiness.Score')

# check the liniarity (to check that a linear relationship exists between the dependent variable (y = happiness) and the independent variables)
plotData([x[0] for x in inputs], outputs, [], [], [], [], 'Capita GDP vs. happiness')
plotData([x[1] for x in inputs], outputs, [], [], [], [], 'Freedom vs. happiness')

# split data into training data (80%) and testing data (20%)
np.random.seed(5)
indexes = [i for i in range(len(inputs))]
trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace = False)
validationSample = [i for i in indexes  if not i in trainSample]
trainInputs = [inputs[i] for i in trainSample]
trainOutputs = [outputs[i] for i in trainSample]
validationInputs = [inputs[i] for i in validationSample]
validationOutputs = [outputs[i] for i in validationSample]

# create a LinearRegression object and fit the model
model = LinearRegression()
model.fit(trainInputs, trainOutputs)

# get the coefficients
w0, w1, w2 = model.w0, model.w1, model.w2

# plot the model
xx1 = [[el, min([x[1] for x in inputs])] for el in [min([x[0] for x in inputs]), max([x[0] for x in inputs])]]
xx2 = [[min([x[0] for x in inputs]), el] for el in [min([x[1] for x in inputs]), max([x[1] for x in inputs])]]
yref = [w0 + w1 * el[0] + w2 * el[1] for el in xx1]
plotData([x[0] for x in inputs], outputs, xx1, yref, [], [], title = "Capita GDP vs. happiness and Freedom vs. happiness")

# make predictions for test data
computedValidationOutputs = model.predict(validationInputs)
plotData([], [], validationOutputs, computedValidationOutputs, validationOutputs, validationOutputs, "predictions vs real test data")

error = 0.0
for t1, t2 in zip(computedValidationOutputs, validationOutputs):
    error += (t1 - t2) ** 2
error = error / len(validationOutputs)
print("prediction error (manual): ", error)

# compute the differences between the predictions and real outputs
error = mean_squared_error(validationOutputs, computedValidationOutputs)
print("prediction error: ", error)
