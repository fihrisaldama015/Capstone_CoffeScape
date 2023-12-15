const {
  addData,
  getArabica,
  getArabicaById,
} = require("./handler/coffeeHandler");

const {
  addRating,
  addCoffeeRating,
  removeRatingCoffee,
} = require("./handler/userRatingHandler");

const { registerUser, loginUser } = require("./handler/authHandler");
const {
  getAllUsers,
  updateUser,
  getUserById,
  forgotPassword,
} = require("./handler/userHandler");
const {drink} = require("./handler/moodHandler");
const {
  addFavoriteCoffee,
  removeFavoriteCoffee,
  getFavoriteCoffee,
} = require("./handler/userFavoriteHandler");

const authRoute = [
  {
    method: "POST",
    path: "/register",
    handler: registerUser,
  },
  {
    method: "POST",
    path: "/login",
    handler: loginUser,
  },
];

const userRoute = [
  {
    method: "GET",
    path: "/users",
    handler: getAllUsers,
  },
  {
    method: "GET",
    path: "/users/{id}",
    handler: getUserById,
  },
  {
    method: "PUT",
    path: "/users/{id}",
    handler: updateUser,
  },
  {
    method: "PUT",
    path: "/forgotpassword",
    handler: forgotPassword,
  },
];

const recipesRoute = [
  {
    method: "GET",
    path: "/add",
    handler: addData,
  },
  {
    method: "GET",
    path: "/arabica",
    handler: getArabica,
  },
  {
    method: "GET",
    path: "/arabica/{id}",
    handler: getArabicaById,
  },
];

const userFavoriteRoute = [
  {
    method: "POST",
    path: "/users/{id}/favorite",
    handler: addFavoriteCoffee,
  },
  {
    method: "DELETE",
    path: "/users/{id}/favorite",
    handler: removeFavoriteCoffee,
  },
  {
    method: "GET",
    path: "/users/{id}/favorite",
    handler: getFavoriteCoffee,
  },
];

const coffeeRatingRoute = [
  {
    method: "GET",
    path: "/adddatarating",
    handler: addRating,
  },
  {
    method: "POST",
    path: "/users/{id}/rating",
    handler: addCoffeeRating,
  },
  {
    method: "DELETE",
    path: "/users/{id}/rating",
    handler: removeRatingCoffee,
  },
];

const moodBasedRoute = [
  {
    method: "GET",
    path: "/mood/{mood}",
    handler: drink,
  },
];

const routes = [
  ...authRoute,
  ...userRoute,
  ...recipesRoute,
  ...userFavoriteRoute,
  ...coffeeRatingRoute,
  ...moodBasedRoute,
];

module.exports = routes;
