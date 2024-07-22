import numpy as np

def sigmoid(x):
    return 1 / (1 + np.exp(-x))

def sigmoid_derivative(x):
    return x * (1 - x)

def softmax(x):
    exp_x = np.exp(x)
    return exp_x / np.sum(exp_x, axis=1, keepdims=True)

class MLPClassifier:
    def __init__(self, input_size, hidden_size, output_size):
        self.input_size = input_size
        self.hidden_size = hidden_size
        self.output_size = output_size

        # Initialize weights
        self.weights1 = np.random.randn(self.input_size, self.hidden_size)
        self.weights2 = np.random.randn(self.hidden_size, self.output_size)

    def sigmoid(self, x):
        return 1 / (1 + np.exp(-x))

    def sigmoid_derivative(self, x):
        return x * (1 - x)

    def forward(self, inputs):
        # Forward pass
        self.hidden_layer = self.sigmoid(np.dot(inputs, self.weights1))
        self.output_layer = self.sigmoid(np.dot(self.hidden_layer, self.weights2))

        return self.output_layer

    def backward(self, inputs, targets, learning_rate):
        # Backward pass
        output_error = targets - self.output_layer
        output_delta = output_error * self.sigmoid_derivative(self.output_layer)

        hidden_error = np.dot(output_delta, self.weights2.T)
        hidden_delta = hidden_error * self.sigmoid_derivative(self.hidden_layer)

        # Update weights
        self.weights2 += learning_rate * np.dot(self.hidden_layer.T, output_delta)
        self.weights1 += learning_rate * np.dot(inputs.T, hidden_delta)

    def train(self, inputs, targets, epochs, learning_rate):
        for i in range(epochs):
            # Forward pass
            output = self.forward(inputs)

            # Backward pass
            self.backward(inputs, targets, learning_rate)

            if i % 100 == 0:
                loss = np.mean(np.square(targets - output))
                print(f"Epoch {i}: loss = {loss:.4f}")


class NeuralNetwork:
    def __init__(self, input_size, hidden_size, output_size):
        self.W1 = np.random.randn(input_size, hidden_size)
        self.b1 = np.zeros((1, hidden_size))
        self.W2 = np.random.randn(hidden_size, output_size)
        self.b2 = np.zeros((1, output_size))

    def forward(self, X):
        self.z1 = np.dot(X, self.W1) + self.b1
        self.a1 = sigmoid(self.z1)
        self.z2 = np.dot(self.a1, self.W2) + self.b2
        self.y_hat = softmax(self.z2)
        return self.y_hat

    def backward(self, X, y, learning_rate):
        m = X.shape[0]

        # calculate gradients
        dZ2 = self.y_hat - y
        dW2 = 1 / m * np.dot(self.a1.T, dZ2)
        db2 = 1 / m * np.sum(dZ2, axis=0, keepdims=True)
        dZ1 = np.dot(dZ2, self.W2.T) * self.a1 * (1 - self.a1)
        dW1 = 1 / m * np.dot(X.T, dZ1)
        db1 = 1 / m * np.sum(dZ1, axis=0, keepdims=True)

        # update parameters
        self.W2 -= learning_rate * dW2
        self.b2 -= learning_rate * db2
        self.W1 -= learning_rate * dW1
        self.b1 -= learning_rate * db1

    def train(self, X, y, learning_rate, num_iterations):
        for i in range(num_iterations):
            y_hat = self.forward(X)
            self.backward(X, y, learning_rate)
            if i % 100 == 0:
                loss = -np.mean(y * np.log(y_hat))
                print(f'Iteration {i}, loss {loss}')

    def predict(self, X):
        y_hat = self.forward(X)
        return np.argmax(y_hat, axis=1)
