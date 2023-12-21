const { db, FieldValue } = require("../lib/firebase");

const addFavoriteCoffee = async (request, h) => {
  try {
    const { id } = request.params;
    const { coffeeId } = request.payload;
    if (!coffeeId) {
      const response = h.response({
        status: "fail",
        message: "Please fill all field, `coffeeId`",
      });
      response.code(400);
      return response;
    }
    const user = await db.collection("users").doc(id).get();
    if (!user.exists) {
      const response = h.response({
        status: "fail",
        message: "User not found",
      });
      response.code(404);
      return response;
    }
    const recipeExist = await db.collection("recipes").doc(coffeeId).get();

    if (!recipeExist.exists) {
      const response = h.response({
        status: "fail",
        message: "Recipe with id:`" + coffeeId + "` not found",
      });
      response.code(404);
      return response;
    }

    const userRef = db.collection("users").doc(id);
    await userRef.set(
      {
        favoriteCoffee: FieldValue.arrayUnion(coffeeId),
      },
      { merge: true }
    );
    const response = h.response({
      status: "success",
      message: "add favorite coffee successfully",
      data: {
        id: user.id,
        email: user.data().email,
        name: user.data().name,
      },
    });
    response.code(201);
    return response;
  } catch (error) {
    console.log(error);
    const response = h.response({
      status: "fail",
      message: "add favorite coffee failed: " + error,
    });
    response.code(400);
    return response;
  }
};

const removeFavoriteCoffee = async (request, h) => {
  try {
    const { id } = request.params;
    const { coffeeId } = request.payload;
    if (!coffeeId) {
      const response = h.response({
        status: "fail",
        message: "Please fill all field, `coffeeId`",
      });
      response.code(400);
      return response;
    }
    const user = await db.collection("users").doc(id).get();
    if (!user.exists) {
      const response = h.response({
        status: "fail",
        message: "User not found",
      });
      response.code(404);
      return response;
    }
    const recipeExist = await db.collection("recipes").doc(coffeeId).get();

    if (!recipeExist.exists) {
      const response = h.response({
        status: "fail",
        message: "Recipe with id:`" + coffeeId + "` not found",
      });
      response.code(404);
      return response;
    }

    const favoriteCoffeeExist = user.data().favoriteCoffee.includes(coffeeId);

    if (!favoriteCoffeeExist) {
      const response = h.response({
        status: "fail",
        message: "Recipe with id:`" + coffeeId + "` not found in favorite",
      });
      response.code(404);
      return response;
    }

    const userRef = db.collection("users").doc(id);
    await userRef.set(
      {
        favoriteCoffee: FieldValue.arrayRemove(coffeeId),
      },
      { merge: true }
    );
    const response = h.response({
      status: "success",
      message: "remove favorite coffee successfully",
      data: {
        id: user.id,
        email: user.data().email,
        name: user.data().name,
      },
    });
    response.code(201);
    return response;
  } catch (error) {
    console.log(error);
    const response = h.response({
      status: "fail",
      message: "remove favorite coffee failed: " + error,
    });
    response.code(400);
    return response;
  }
};

const getFavoriteCoffee = async (request, h) => {
  try {
    const { id } = request.params;
    const user = await db.collection("users").doc(id).get();
    if (!user.exists) {
      const response = h.response({
        status: "fail",
        message: "User not found",
      });
      response.code(404);
      return response;
    }

    const favoriteCoffee = user.data().favoriteCoffee || [];
    const favoriteCoffeeData = [];
    for (let i = 0; i < favoriteCoffee.length; i++) {
      const docRef = db.collection("recipes").doc(favoriteCoffee[i]);
      const doc = await docRef.get();
      if (doc.exists) {
        const data = doc.data();
        const arabicaRecipe = {
          id: doc.id,
          name: data.name,
          FlavorProfiles: data.FlavorProfiles,
          RoastLevel: data.RoastLevel,
          ServingStyle: data.ServingStyle,
          RecommendedBeans: data.RecommendedBeans,
          BrewingMethod: data.BrewingMethod,
          rating: data.rating,
          thumbnail: data.thumbnail,
        };
        favoriteCoffeeData.push(arabicaRecipe);
      }
    }
    const response = h.response({
      status: "success",
      message: "get favorite coffee successfully",
      data: favoriteCoffeeData,
    });
    response.code(200);
    return response;
  } catch (error) {
    console.log(error);
    const response = h.response({
      status: "fail",
      message: "get favorite coffee failed: " + error,
    });
    response.code(400);
    return response;
  }
};

module.exports = { addFavoriteCoffee, removeFavoriteCoffee, getFavoriteCoffee };
