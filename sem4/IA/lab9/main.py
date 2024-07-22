import matplotlib.pyplot as plt
from sklearn.preprocessing import StandardScaler
import numpy as np
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import OneHotEncoder
from NeuralNetwork import MLPClassifier

# functii utile
def splitData(inputs, outputs):
    np.random.seed(5)
    indexes = [i for i in range(len(inputs))]
    trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
    testSample = [i for i in indexes if not i in trainSample]

    trainInputs = [inputs[i] for i in trainSample]
    trainOutputs = [outputs[i] for i in trainSample]
    testInputs = [inputs[i] for i in testSample]
    testOutputs = [outputs[i] for i in testSample]

    return trainInputs, trainOutputs, testInputs, testOutputs


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


def training(classifier, trainInputs, trainOutputs):
    # step3: training the classifier
    # identify (by training) the classification model
    classifier.train(trainInputs, trainOutputs)


def classification(classifier, testInputs):
    # step4: testing (predict the labels for new inputs)
    # makes predictions for test data
    computedTestOutputs = classifier.predict(testInputs)

    return computedTestOutputs


def plotConfusionMatrix(cm, classNames, title):
    from sklearn.metrics import confusion_matrix
    import itertools

    classes = classNames
    plt.figure()
    plt.imshow(cm, interpolation = 'nearest', cmap = 'Blues')
    plt.title('Confusion Matrix ' + title)
    plt.colorbar()
    tick_marks = np.arange(len(classNames))
    plt.xticks(tick_marks, classNames, rotation=45)
    plt.yticks(tick_marks, classNames)

    text_format = 'd'
    thresh = cm.max() / 2.
    for row, column in itertools.product(range(cm.shape[0]), range(cm.shape[1])):
        plt.text(column, row, format(cm[row, column], text_format),
                horizontalalignment = 'center',
                color = 'white' if cm[row, column] > thresh else 'black')

    plt.ylabel('True label')
    plt.xlabel('Predicted label')
    plt.tight_layout()

    plt.show()

def evalMultiClass(realLabels, computedLabels, labelNames):
    from sklearn.metrics import confusion_matrix

    confMatrix = confusion_matrix(realLabels, computedLabels)
    acc = sum([confMatrix[i][i] for i in range(len(labelNames))]) / len(realLabels)
    precision = {}
    recall = {}
    for i in range(len(labelNames)):
        precision[labelNames[i]] = confMatrix[i][i] / sum([confMatrix[j][i] for j in range(len(labelNames))])
        recall[labelNames[i]] = confMatrix[i][i] / sum([confMatrix[i][j] for j in range(len(labelNames))])
    return acc, precision, recall, confMatrix

# Load iris dataset
iris = load_iris()

# Split data into training and testing sets
input_train, input_test, output_train, output_test = train_test_split(iris.data, iris.target, test_size=0.2)
label_names = iris['target_names']

# One-hot encode the target variable
encoder = OneHotEncoder()
normalised_train_output = encoder.fit_transform(output_train.reshape(-1, 1)).toarray()

#train
ANN = MLPClassifier(4, 10, 3)
ANN.train(input_train, normalised_train_output, 1000, 0.01)


#Predict
predicted_output = ANN.forward(input_test)
predictions = np.argmax(predicted_output, axis=1)

accuracy, precision, recall, confusionMatrix = evalMultiClass(output_test, predictions, label_names)
print(f'Accuracy: {accuracy}')

plotConfusionMatrix(confusionMatrix, label_names, 'confusion matrix for iris')

# step2: split data into train and test

def data2FeaturesMoreClasses(inputs, outputs):
    labels = set(outputs)
    noData = len(inputs)
    for crtLabel in labels:
        x = [inputs[i][0] for i in range(noData) if outputs[i] == crtLabel ]
        y = [inputs[i][1] for i in range(noData) if outputs[i] == crtLabel ]
        plt.scatter(x, y, label = label_names[crtLabel])
    plt.xlabel('feat1')
    plt.ylabel('feat2')
    plt.legend()
    plt.show()


# plot the data in order to observe the shape of the classifier required in this problem
data2FeaturesMoreClasses(input_train, output_train)


print("Demo 2 number recognition")
from sklearn.metrics import confusion_matrix
from NeuralNetwork import NeuralNetwork
from sklearn.datasets import load_digits

digits = load_digits()

input = digits.data / 16.0  # scale pixel values to be between 0 and 1
output = digits.target

input_train, input_test, output_train, output_test = train_test_split(input, output, test_size=0.2)

input_size = input_train.shape[1]
hidden_size = 16
output_size = 10

ANN = NeuralNetwork(input_size, hidden_size, output_size)
ANN.train(input_train, np.eye(10)[output_train], learning_rate=0.1, num_iterations=1000)

output_predicted = ANN.predict(input_test)

accuracy = np.mean(output_predicted == output_test)
print(f'Accuracy: {accuracy}')

cm = confusion_matrix(output_test, output_predicted)
plotConfusionMatrix(cm, [], 'nush')
