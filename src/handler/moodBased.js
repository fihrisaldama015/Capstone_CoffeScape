const { db } = require("../lib/firebase");

const getHappyDrinks = async () => {
  const response = await db
    .collection("recipes")
    .where("moodType", "==", "Happy")
    .get();

  let drinks = [];
  response.forEach((doc) => {
    drinks.push(doc.data());
  });

  return drinks;
};

const getSadDrinks = async () => {
  const response = await db
    .collection("recipes")
    .where("moodType", "==", "Sad")
    .get();
  let drinks = [];
  response.forEach((doc) => {
    drinks.push(doc.data());
  });

  return drinks;
};

const getLonelyDrinks = async () => {
  const response = await db
    .collection("recipes")
    .where("moodType", "==", "Lonely")
    .get();
  let drinks = [];
  response.forEach((doc) => {
    drinks.push(doc.data());
  });
  return drinks;
};

const getBoredDrinks = async () => {
  const response = await db
    .collection("recipes")
    .where("moodType", "==", "Bored")
    .get();

  let drinks = [];
  response.forEach((doc) => {
    drinks.push(doc.data());
  });

  return drinks;
};

async function suggestDrink(mood) {
  let drinks;
  switch (mood.toLowerCase()) {
    case "happy":
      drinks = await getHappyDrinks();
      break;
    case "sad":
      drinks = await getSadDrinks();
      break;
    case "lonely":
      drinks = await getLonelyDrinks();
      break;
    case "bored":
      drinks = await getBoredDrinks();
      break;
    default:
      return "Invalid mood. Please choose from: happy, sad, lonely, or bored.";
  }

  let selectedDrink = drinks[Math.floor(Math.random() * drinks.length)];
  return {
    message: `For your ${mood} mood, I suggest a ${selectedDrink.name}.`,
    data: selectedDrink,
  };
}

module.exports = { suggestDrink };
