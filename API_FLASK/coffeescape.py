# =============================== BAD WORDS PACKAGE ===============================

import tensorflow as tf
from tensorflow.keras.layers import TextVectorization
from flask import Flask, request, jsonify
import pandas as pd

# =============================== BAD WORDS PACKAGE ===============================

# =============================== COFFEE RECOMMENDATION PACKAGE ===============================

import pickle
import numpy as np

# =============================== COFFEE RECOMMENDATION PACKAGE ===============================

# =============================== BAD WORDS MODEL ===============================

model = tf.keras.models.load_model('model.h5')

vectorizer = TextVectorization(max_tokens=20000, output_sequence_length=200, output_mode='int')

df = pd.read_csv('train.csv')
df.head()
X = df['comment_text']

vectorizer.adapt(X.values) # same data as in training

def preprocess_text(text):
    text_vectorized = vectorizer(text)
    return text_vectorized

# =============================== BAD WORDS MODEL ===============================

# =============================== COFFEE RECOMMENDATION MODEL ===============================

filename = 'finalized_model.pkl'
item_similarity = pickle.load(open(filename, 'rb'))

# =============================== COFFEE RECOMMENDATION MODEL ===============================

app = Flask(__name__)

@app.route('/')
def index():
    return 'Welcome to the model API!'


# =============================== BAD WORDS ===============================

@app.route('/predict', methods=['POST'])
def predict():
    try:
        input_text = request.json['text']

        input_text_vectorized = preprocess_text([input_text])

        prediction = model.predict(input_text_vectorized)

        result_array = (prediction>0.5).astype(int).tolist()[0]

        classification = ["toxic","severe_toxic","obscene","threat","insult","identity_hate"]
        
        classified_result = []
        index=0
        for i in result_array:
            if (i == 1):
                classified_result.append(classification[index])
            index+=1
        result = {'prediction:': classified_result}

        return jsonify(result)

    except Exception as e:
        return jsonify({'error': str(e)})

# =============================== BAD WORDS ===============================

# =============================== COFFEE RECOMMENDATION ===============================

@app.route('/recommend', methods=['POST'])
def recommend():
    try:
        data = request.json['data']

        new_user_ratings = np.zeros(item_similarity.shape[0])
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

        N = 10
        recommended_item_indices = np.argsort(recommendation_scores)[::-1][:N]

        top_recomendation = recommended_item_indices[0][:10]
        response = make_response(
            jsonify(
                {'prediction': top_recomendation.tolist()}
            ),
            200,
        )
        response.headers['Content-Type'] = 'application/json'
        return response

    except Exception as e:
        return jsonify({'error': str(e)})

# =============================== COFFEE RECOMMENDATION ===============================

if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0", port="5000")
