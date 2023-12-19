from sklearn.model_selection import train_test_split
import pandas as pd
import numpy as np
from sklearn.preprocessing import StandardScaler
import re
from sklearn.metrics.pairwise import cosine_similarity

data = pd.read_csv('NEW_Dataset Capstone.xlsx - Rating Data (5).csv')

user_ids = data.columns[2:]
item_ids = data['Coffee IDs'].tolist()
print("User IDs:", user_ids)
print("Item IDs:", item_ids)

ratings_matrix = data.iloc[:, 2:].values
print("Subset of Ratings Matrix:")
print(ratings_matrix[:, :5])
ratings_matrix = np.nan_to_num(ratings_matrix, nan=np.nanmean(ratings_matrix))
scaler = StandardScaler()
ratings_matrix_normalized = scaler.fit_transform(ratings_matrix)
X_train, X_test = train_test_split(ratings_matrix, test_size=0.1, random_state=42)
print("Subset of X_train:")
print(X_train[:10 :10])
num_users = len(user_ids)
num_items = len(item_ids)
print(num_items)
print(num_users)

item_similarity = cosine_similarity(ratings_matrix)
new_user_ratings = np.zeros(ratings_matrix.shape[0])
new_user_ratings[0] = 2
new_user_ratings[1] = 4
new_user_ratings[2] = 3
new_user_ratings[7] = 1
new_user_ratings[5] = 5
new_user_ratings[8] = 2
new_user_ratings[41] = 3
new_user_ratings[24] = 4
weighted_similarities = item_similarity.dot(new_user_ratings)
sum_abs_similarities = np.array([np.abs(item_similarity).sum(axis=1)])
recommendation_scores = weighted_similarities / sum_abs_similarities
recommended_item_indices = np.argsort(recommendation_scores)[::-1]
N = 10
top_recommendations = recommended_item_indices[0][:N]
print("Top {} recommended items for the new user:".format(N))
print(", ".join(map(str, top_recommendations)))

import pickle

filename = 'finalized_model.pkl'
pickle.dump(item_similarity, open(filename, 'wb'))