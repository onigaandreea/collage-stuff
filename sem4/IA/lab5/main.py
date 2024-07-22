from math import sqrt
import matplotlib.pyplot as plt
import numpy as np

# Problem specification:
# input: realOutputs, computedOutputs - arrays of the same length containing real numbers
# output: error - real value

realOutputs = [7.53, 7.52, 7.5, 7.49, 7.46]
computedOutputs = [7.8, 7.75, 7.45, 7.6, 7.4]

# plot the data
indexes = [i for i in range(len(realOutputs))]
real, = plt.plot(indexes, realOutputs, 'ro', label = 'real')
computed, = plt.plot(indexes, computedOutputs, 'bo', label = 'computed')
plt.xlim(-0.5,5)
plt.ylim(7, 8)
plt.legend([real, (real, computed)], ["Real", "Computed"])
#plt.show()

# compute the prediction error

# MAE
errorL1 = sum(abs(r - c) for r, c in zip(realOutputs, computedOutputs)) / len(realOutputs)
print('Error (L1): ', errorL1)

# RMSE
errorL2 = sqrt(sum((r - c) ** 2 for r, c in zip(realOutputs, computedOutputs)) / len(realOutputs))
print('Error (L2): ', errorL2)

# consider some real labels and some predicted labels (obtained by the ML algorithm - a classifier)
# we want ot estimate the error of prediction (classification)

# Problem specification:
# input: realLabels, computedLabels - arrays of the same length containing binary labels (some discrete values)
# output: accuracy, precision, recall - real values in range [0,1]


# a balanced data set (each class containes the same numer of samples)

realLabels = ['spam', 'spam', 'ham', 'ham', 'spam', 'ham']
computedLabels = ['spam', 'ham', 'ham', 'spam', 'spam', 'ham']


# suppose that 'spam' is the positive class (and 'ham' is the negative class)
# compute the prediction performance


def evalClassificationV2(realLabels, computedLabels, pos, neg):
    # noCorrect = 0
    # for i in range(0, len(realLabels)):
    #     if (realLabels[i] == computedLabels[i]):
    #         noCorrect += 1
    # acc = noCorrect / len(realLabels)
    acc = sum([1 if realLabels[i] == computedLabels[i] else 0 for i in range(0, len(realLabels))]) / len(realLabels)

    # TP = 0
    # for i in range(0, len(realLabels)):
    #     if (realLabels[i] == 'spam' and computedLabels[i] == 'spam'):
    #         TP += 1
    TP = sum([1 if (realLabels[i] == pos and computedLabels[i] == pos) else 0 for i in range(len(realLabels))])
    FP = sum([1 if (realLabels[i] == neg and computedLabels[i] == pos) else 0 for i in range(len(realLabels))])
    TN = sum([1 if (realLabels[i] == neg and computedLabels[i] == neg) else 0 for i in range(len(realLabels))])
    FN = sum([1 if (realLabels[i] == pos and computedLabels[i] == neg) else 0 for i in range(len(realLabels))])

    precisionPos = TP / (TP + FP)
    recallPos = TP / (TP + FN)
    precisionNeg = TN / (TN + FN)
    recallNeg = TN / (TN + FP)

    precision = [precisionPos, precisionNeg]
    recall = [recallPos, recallNeg]

    return acc, precision, recall


acc, prec, recall = evalClassificationV2(realLabels, computedLabels, 'spam', 'ham')

print('acc: ', acc, ' precision: ', prec, ' recall: ', recall)

import csv

def calcError():
    # Open the CSV file
    with open('sport.csv', newline='') as csvfile:
        # Create a CSV reader object
        csv_reader = csv.reader(csvfile, delimiter=',', quotechar='"')
        weight = []
        waist = []
        pulse = []
        predictedweight = []
        predictedwaist = []
        predictedpulse = []
        # Loop through each row in the CSV file
        for row in csv_reader:
            # Access the data in each column by index
            weight.append(int(row[0]))
            waist.append(int(row[1]))
            pulse.append(int(row[2]))
            predictedweight.append(int(row[3]))
            predictedwaist.append(int(row[4]))
            predictedpulse.append(int(row[5]))

        # MAE
        weightError1 = sum(abs(r - c) for r, c in zip(weight, predictedweight)) / len(weight)
        print('Weight error (L1): ', weightError1)

        # RMSE
        weightError2 = sqrt(sum((r - c) ** 2 for r, c in zip(weight, predictedweight)) / len(weight))
        print('Weight error (L2): ', weightError2)

        # MAE
        waistError1 = sum(abs(r - c) for r, c in zip(waist, predictedwaist)) / len(waist)
        print('Waist error (L1): ', waistError1)

        # RMSE
        waistError2 = sqrt(sum((r - c) ** 2 for r, c in zip(waist, predictedwaist)) / len(waist))
        print('Waist error (L2): ', waistError2)

        # MAE
        pulseError1 = sum(abs(r - c) for r, c in zip(pulse, predictedpulse)) / len(pulse)
        print('Pulse error (L1): ', pulseError1)

        # RMSE
        pulseError2 = sqrt(sum((r - c) ** 2 for r, c in zip(pulse, predictedpulse)) / len(pulse))
        print('Pulse error (L2): ', pulseError2)


def problema2():
    with open('flowers.csv', newline='') as csvfile:
        # Create a CSV reader object
        csv_reader = csv.reader(csvfile, delimiter=',', quotechar='"')
        type = []
        prediction = []
        # Loop through each row in the CSV file
        for row in csv_reader:
            # Access the data in each column by index
            type.append(row[0])
            prediction.append(row[1])

    acc = sum([1 if type[i] == prediction[i] else 0 for i in range(0, len(type))]) / len(type)

    print("The accuracy is: ", acc)

    # pos = daisy neg = tulip/rose
    TP1 = sum([1 if (type[i] == 'Daisy' and prediction[i] == 'Daisy') else 0 for i in range(len(type))])
    FP1 = sum([1 if ((type[i] == 'Tulip' or type[i] == 'Rose') and prediction[i] == 'Daisy') else 0 for i in range(len(type))])
    FN1 = sum([1 if (type[i] == 'Daisy' and (prediction[i] == 'Tulip' or prediction[i] == 'Rose')) else 0 for i in range(len(type))])

    prec1 = TP1/(TP1 + FP1)

    recall1 = TP1/(TP1 + FN1)

    print("The precision for Daisy is: ", prec1, " and the recall is: ", recall1)

    # pos = tulip neg = daisy/rose
    TP2 = sum([1 if (type[i] == 'Tulip' and prediction[i] == 'Tulip') else 0 for i in range(len(type))])
    FP2 = sum([1 if ((type[i] == 'Daisy' or type[i] == 'Rose') and prediction[i] == 'Tulip') else 0 for i in
               range(len(type))])
    FN2 = sum([1 if (type[i] == 'Tulip' and (prediction[i] == 'Daisy' or prediction[i] == 'Rose')) else 0 for i in
               range(len(type))])

    prec2 = TP2 / (TP2 + FP2)

    recall2 = TP2 / (TP2 + FN2)

    print("The precision for Tulip is: ", prec2, " and the recall is: ", recall2)

    # pos = rose neg = tulip/daisy
    TP3 = sum([1 if (type[i] == 'Rose' and prediction[i] == 'Rose') else 0 for i in range(len(type))])
    FP3 = sum([1 if ((type[i] == 'Tulip' or type[i] == 'Daisy') and prediction[i] == 'Rose') else 0 for i in
               range(len(type))])
    FN3 = sum([1 if (type[i] == 'Rose' and (prediction[i] == 'Tulip' or prediction[i] == 'Daisy')) else 0 for i in
               range(len(type))])

    prec3 = TP3 / (TP3 + FP3)

    recall3 = TP3 / (TP3 + FN3)

    print("The precision for Rose is: ", prec3, " and the recall is: ", recall3)


calcError()
problema2()