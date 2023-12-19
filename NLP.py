import os
import pandas as pd
import tensorflow as tf
import numpy as np
from tensorflow.keras.layers import TextVectorization
print(tf.config.list_physical_devices('GPU'))
df = pd.read_csv('train.csv')
df.head()
X = df['comment_text']
y = df[df.columns[2:]].values

MAX_FEATURES = 200000

vectorizer = TextVectorization(
    max_tokens=MAX_FEATURES,
    output_sequence_length=1800,
    output_mode='int'
)
vectorizer.adapt(X.values)
vectorized_text = vectorizer(X.values)

dataset = tf.data.Dataset.from_tensor_slices((vectorized_text, y))
dataset = dataset.cache()
dataset = dataset.shuffle(160000)
dataset = dataset.batch(32)
dataset = dataset.prefetch(8)

train_size = int(0.7 * len(dataset))
val_size = int(0.2 * len(dataset))
test_size = int(0.1 * len(dataset))

train_set = dataset.take(train_size)
val_set = dataset.skip(train_size).take(val_size)
test_set = dataset.skip(train_size + val_size).take(test_size)

vocab_size= MAX_FEATURES+1
embedding_dim= 32

model=tf.keras.models.Sequential([
    tf.keras.layers.Embedding(input_dim=vocab_size, output_dim=embedding_dim),
    tf.keras.layers.Bidirectional(tf.keras.layers.LSTM(32)),
    tf.keras.layers.Dense(256, activation='relu'),
    tf.keras.layers.Dense(128, activation='relu'),
    tf.keras.layers.Dense(64, activation='relu'),
    tf.keras.layers.Dense(6, activation='sigmoid')
])
model.compile(loss='binary_crossentropy', optimizer='adam')
model.summary()
model.fit(train_set, epochs=1, validation_data=val_set)

test_text = ['This is good! You guys should try this']
test_text_vectorized = vectorizer(test_text)
prediction = model.predict(test_text_vectorized)
print((prediction > 0.5).astype(int))

saved_model_path= "./NLP_model.h5"
model.save(saved_model_path)

