# Bangkit Capstone Project

### This is repository aplication of CoffeeScape

Please change the branch to `cc`, `ml`, or `android` to see each path repository!

# Our Design app

[![figma](https://img.shields.io/badge/Figma-Our%20Design-success)](https://www.figma.com/file/eLblkoTir6PEYUCENZBuUr/Capstone-CoffeeScape?type=design&node-id=0%3A1&mode=design&t=qpNlMpelD9VGAvMd-1)

# Our Member

|           Member            | Student ID  |        Path         |
| :-------------------------: | :---------: | :-----------------: |
|     Irfan Fadli Nugraha     | M008BSY0620 |  Machine Learning   |
|     Hana Dewi Shoviyah      | M283BSX0521 |  Machine Learning   |
| Enas Erliana Zakiya Yudhana | M008BSX0125 |  Machine Learning   |
|    Muhamad Fihris Aldama    | C296BSY4031 |   Cloud Computing   |
|     Rayya Ruwa’im Nafie     | C296BSY3695 |   Cloud Computing   |
|  Talitha Bertha Arvyandita  | A296BSX2694 | Android Development |
|      Lutfi Nur Rohmah       | A015BSX2004 | Android Development |

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


# CoffeeScape API


## Documentation
* [API DOCUMENTATION](https://documenter.getpostman.com/view/21791853/2s9YeD9tAT)

## Server Requirements

<b>Node.js</b> - version 18.18.0 or above.

Link Download Node.js => <a target="_blank" href="https://nodejs.org/en">Click This Link to Download</a>

## Installation

### Clone This Repo

```
git clone -b cc https://github.com/fihrisaldama015/Capstone_CoffeScape.git
cd Capstone_CoffeScape
```

### Install Dependencies

```
npm install
```

wait this installation proccess to complete. it takes 3-5 minutes.

### Create ENV file

copy `.env.example` and rename the file to `.env`

```
JWT_SECRET_KEY= #secret
DATABASE_URL= #https://$PROJECT_ID.firebaseio.com
ML_API_ENDPOINT= #https://$PROJECT_ID.$REGION.appspot.com or http://$EXTERNAL_IP:$PORT
APP_URL= #https://$PROJECT_ID.web.app or http://$EXTERNAL_IP:$PORT
```
edit the file and use your own key and url

### Generate Service Account

1. Create your Firestore database at [Firestore Page](https://console.cloud.google.com/firestore/databases), select project if you haven't.

2. Go to [Service Account Page](https://console.cloud.google.com/projectselector2/iam-admin/serviceaccounts?supportedpurview=project), then select your project (ex: capstone) if you haven't choose project yet.

3. Select one of the service account that have `firebase-adminsdk` in the beginning of the service account name.

4. Move to tab `KEYS`, click `ADD KEY` then select `Create New Key`

5. for key type select `JSON`, then click `CREATE`, the JSON file is downloaded to your local computer

6. Go to the downloaded JSON file directory, copy or move the file to the previous `Capstone_CoffeeScape` folder

### Run the API

```
$ npm run dev
```

you should see like this when the server run successfully

```
> capstone@1.0.0 dev
> nodemon src/server.js

[nodemon] 3.0.1
[nodemon] to restart at any time, enter `rs`
[nodemon] watching path(s): *.*
[nodemon] watching extensions: js,mjs,cjs,json
[nodemon] starting `node src/server.js`
Server running on port 9000
```
