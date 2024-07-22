class LinearRegression:
    def __init__(self):
        self.w0 = 0
        self.w1 = 0
        self.w2 = 0

    def fit(self, X, Y):
        n = len(X)
        sum_x1, sum_x2, sum_y, sum_x1y, sum_x2y, sum_x1x2, sum_x1x1, sum_x2x2 = 0, 0, 0, 0, 0, 0, 0, 0
        for i in range(n):
            x1, x2, y = X[i][0], X[i][1], Y[i]
            sum_x1 += x1
            sum_x2 += x2
            sum_y += y
            sum_x1y += x1 * y
            sum_x2y += x2 * y
            sum_x1x2 += x1 * x2
            sum_x1x1 += x1 * x1
            sum_x2x2 += x2 * x2
        self.w0, self.w1, self.w2 = self.calculate_weights(n, sum_x1, sum_x2, sum_y, sum_x1y, sum_x2y, sum_x1x2,
                                                           sum_x1x1, sum_x2x2)

    def calculate_weights(self, n, sum_x1, sum_x2, sum_y, sum_x1y, sum_x2y, sum_x1x2, sum_x1x1, sum_x2x2):
        w1 = (n * sum_x1y - sum_x1 * sum_y) / (n * sum_x1x1 - sum_x1 * sum_x1)
        w2 = (n * sum_x2y - sum_x2 * sum_y) / (n * sum_x2x2 - sum_x2 * sum_x2)
        w0 = (sum_y - w1 * sum_x1 - w2 * sum_x2) / n
        return w0, w1, w2

    def predict(self, X):
        return [self.w0 + self.w1 * x[0] + self.w2 * x[1] for x in X]
