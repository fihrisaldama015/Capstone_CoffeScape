const {
  addData,
  getcoffee,
  getcoffeeById,
} = require("./handler/coffeeHandler");

const {
  addRating,
  addCoffeeRating,
  removeRatingCoffee,
  getUserRating,
} = require("./handler/userRatingHandler");

const { registerUser, loginUser } = require("./handler/authHandler");
const {
  getAllUsers,
  updateUser,
  getUserById,
  forgotPassword,
} = require("./handler/userHandler");
const { drink } = require("./handler/moodHandler");
const {
  addFavoriteCoffee,
  removeFavoriteCoffee,
  getFavoriteCoffee,
} = require("./handler/userFavoriteHandler");
const { getCoffeeRecommendation } = require("./handler/predictHandler");

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
    path: "/coffee",
    handler: getcoffee,
  },
  {
    method: "GET",
    path: "/coffee/{id}",
    handler: getcoffeeById,
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
  {
    method: "GET",
    path: "/users/{id}/rating",
    handler: getUserRating,
  },
];

const moodBasedRoute = [
  {
    method: "GET",
    path: "/mood/{mood}",
    handler: drink,
  },
];

const predictRoute = [
  {
    method: "GET",
    path: "/recommendation/{id}",
    handler: getCoffeeRecommendation,
  },
];

const routes = [
  ...authRoute,
  ...userRoute,
  ...recipesRoute,
  ...userFavoriteRoute,
  ...coffeeRatingRoute,
  ...moodBasedRoute,
  ...predictRoute,
];

module.exports = routes;
