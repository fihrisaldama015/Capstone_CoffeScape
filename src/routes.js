const {
  addData,
  getArabica,
  getArabicaById,
} = require("./handler/arabicaHandler");

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

const {
  addFavoriteCoffee,
  removeFavoriteCoffee,
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

const routes = [
  ...authRoute,
  ...userRoute,
  ...recipesRoute,
  ...userFavoriteRoute,
  ...coffeeRatingRoute,
];

module.exports = routes;
