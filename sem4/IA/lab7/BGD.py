import random

import numpy as np


class MyBGDRegression:
    def __init__(self, batch_size, learning_rate=0.01, epochs=1000):
        self.learning_rate = learning_rate
        self.epochs = epochs
        self.batch_size = batch_size
        self.coef_ = None
        self.intercept_ = None

    def fit(self, X, y):
        X = np.array(X)
        y = np.array(y)
        n_samples, n_features = X.shape
        self.coef_ = [0.0] * n_features
        self.intercept_ = 0.0

        for epoch in range(self.epochs):
            X_shuffled, y_shuffled = self._shuffle_data(X, y)
            for i in range(0, n_samples, self.batch_size):
                X_batch = X_shuffled[i:i + self.batch_size]
                y_batch = y_shuffled[i:i + self.batch_size]
                grad_coef = [0.0] * n_features
                grad_intercept = 0.0
                for j in range(len(X_batch)):
                    y_pred = self._predict_row(X_batch[j])
                    error = y_pred - y_batch[j]
                    grad_coef = [grad_coef[k] + error * X_batch[j][k] for k in range(n_features)]
                    grad_intercept += error
                self.coef_ = [self.coef_[k] - self.learning_rate * grad_coef[k] / len(X_batch) for k in
                              range(n_features)]
                self.intercept_ -= self.learning_rate * grad_intercept / len(X_batch)

    def predict(self, X):
        return [self._predict_row(row) for row in X]

    def _predict_row(self, row):
        return sum([row[k] * self.coef_[k] for k in range(len(self.coef_))]) + self.intercept_

    def _shuffle_data(self, X, y):
        shuffled_X, shuffled_y = [], []
        indexes = [i for i in range(len(X))]
        random.shuffle(indexes)
        for i in indexes:
            shuffled_X.append(X[i])
            shuffled_y.append(y[i])
        return shuffled_X, shuffled_y
