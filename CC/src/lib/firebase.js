// const { initializeApp } = require("firebase/app");
// const { getAuth } = require("firebase/auth");
// const { getFirestore } = require("firebase/firestore");
// const { GoogleAuthProvider } = require("firebase/auth");

const admin = require("firebase-admin");
const { FieldValue } = require("firebase-admin/firestore");
const serviceAccount = require("../../serviceAccount.json"); // Replace with your service account key

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: process.env.DATABASE_URL, // Replace with your database URL
});

const db = admin.firestore();

// const firebaseConfig = {
//   apiKey: `${process.env.NEXT_PUBLIC_API_KEY}`,
//   authDomain: `${process.env.NEXT_PUBLIC_AUTH_DOMAIN}`,
//   projectId: `${process.env.NEXT_PUBLIC_PROJECT_ID}`,
//   storageBucket: `${process.env.NEXT_PUBLIC_STORAGE_BUCKET}`,
//   messagingSenderId: `${process.env.NEXT_PUBLIC_MESSAGING_SENDER_ID}`,
//   appId: `${process.env.NEXT_PUBLIC_APP_ID}`,
// };

// const provider = new GoogleAuthProvider();

// const app = initializeApp(firebaseConfig);

// const auth = getAuth(app);
// const db = getFirestore(app);

module.exports = { db, FieldValue };
