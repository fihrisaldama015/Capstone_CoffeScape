# Natural Language Processing, Collaborative Filtering, and Mood Based Recommendation.
This repository contains three scripts for different tasks: sentiment analysis using deep learning, collaborative filtering using cosine similarity, and mood based recommendation using switch case to initiate randomly generated value based on each mood group.
# Sentiment Analysis with Deep Learning.
### Requirement
- Python 3.x
- TensorFlow 2.x
- Pandas
- NumPy
- Scikit-Learn
## Instruction
#### 1. Install Dependencies
`pip install tensorflow pandas numpy scikit-learn`
#### 2. Download The Dataset
For the sentiment analysis task, download the 'train.csv' file and place it in the same directory as the script.
#### 3. Run the Sentiment Analysis Script
`NLP.py`
This script loads the dataset, preprocesses the text data, creates a deep learning model for sentiment analysis, and saves the trained model as 'NLP_model.h5'.
#### 4. Testing the trained model.
Edit the `test_text` variable in the script with your own text and run the script to get sentiment predictions.
## Model Details
- The deep learning model architecture consists of an embedding layer, bidirectional LSTM layer, and several dense layers.
- The model is trained using binary crossentropy loss and the Adam optimizer.
# Collaborative Filtering with Cosine Similarity.
### Requirement
- Python 3.x
- Pandas
- NumPy
- Scikit-Learn
## Instruction
#### 1. Install Dependencies
`pip install pandas numpy scikit-learn`
#### 2. Download The Collaborative Filtering Dataset
For the collaborative filtering task, download the 'NEW_Dataset Capstone.xlsx - Rating Data (5).csv' file and place it in the same directory as the script.
#### 3. Run The Collaborative Filtering Script
`python collaborative_filtering.py`
This script reads the dataset, computes item similarity using cosine similarity, generates new user ratings, and recommends top items for the new user.
#### 4. Save the model
The script saves the item similarity matrix using pickle as 'finalized_model.pkl'.
## Model Details
- Collaborative filtering is implemented using cosine similarity between items based on user ratings.
- The script demonstrates how to recommend items for a new user.
# Mood Based Recommendation
### Requirements
JavaScript-enabled environment (browser, Node.js, etc.)
## Instruction
#### 1. Get the data
Get the data from the dataset for mood based and put them inside the list of each variables. Ensure you have access to the dataset containing mood-based drink information.
#### 2. Run the script
Open the HTML file containing the script in a browser or execute the script using Node.js.
#### 3. Enter Your Mood
When prompted, enter your mood (happy, sad, lonely, or bored).
#### 4. View the Suggested Drink
The script will output a suggestion based on your mood.

# Deploy ML Model

## Server Requirements

<b>Python</b> - version 3.8 or above.

## Installation

### Clone This Repo

```
git clone -b ml https://github.com/fihrisaldama015/Capstone_CoffeScape.git
cd Capstone_CoffeScape/API_FLASK
```
Clone the `ml` branch & go to the `API_FLASK` folder directory

### Install Dependencies

```
pip install --user tensorflow
pip install --user flask
pip install --user pandas
pip install --user pickle
pip install --user numpy
```

### Run the ML API

```
$ python3 coffeescape.py
```

the server run on port 5000
