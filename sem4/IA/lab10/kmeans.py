import numpy as np


class KMeans:
    def __init__(self, n_clusters, max_iter=100):
        self.n_clusters = n_clusters
        self.max_iter = max_iter

    def fit(self, X):
        self.centroids = self._initialize_centroids(X)
        for _ in range(self.max_iter):
            old_centroids = np.copy(self.centroids)
            self.labels = self._assign_labels(X)
            self.centroids = self._update_centroids(X)
            if np.all(old_centroids == self.centroids):
                break

    def predict(self, X):
        distances = self._calculate_distances(X)
        return np.argmin(distances, axis=1)

    def _initialize_centroids(self, X):
        indices = np.random.choice(range(len(X)), size=self.n_clusters, replace=False)
        return X[indices]

    def _assign_labels(self, X):
        distances = self._calculate_distances(X)
        return np.argmin(distances, axis=1)

    def _update_centroids(self, X):
        centroids = np.zeros_like(self.centroids)
        for cluster in range(self.n_clusters):
            cluster_points = X[self.labels == cluster]
            if len(cluster_points) > 0:
                centroids[cluster] = np.mean(cluster_points, axis=0)
        return centroids

    def _calculate_distances(self, X):
        distances = np.zeros((len(X), self.n_clusters))
        for cluster in range(self.n_clusters):
            centroid = self.centroids[cluster]
            distances[:, cluster] = np.linalg.norm(X - centroid, axis=1)
        return distances