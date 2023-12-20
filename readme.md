# Bangkit Capstone Project

### This is repository aplication of CoffeeScape

Please change the branch to `cc`, `ml`, or `md` to see each path repository!

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
