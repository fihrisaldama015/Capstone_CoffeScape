const arabica = require("../arabica");
const { db } = require("../lib/firebase");

const getArabica = async (request, h) => {
  try {
    let arabica = [];
    await db
      .collection("recipes")
      .get()
      .then((querySnapshot) => {
        querySnapshot.forEach((doc) => {
          const temp = {
            id: doc.id,
            name: doc.data().name,
            FlavorProfiles: doc.data().FlavorProfiles,
            RoastLevel: doc.data().RoastLevel,
            ServingStyle: doc.data().ServingStyle,
            RecommendedBeans: doc.data().RecommendedBeans,
            BrewingMethod: doc.data().BrewingMethod,
            rating: doc.data().rating,
          };
          arabica.push(temp);
        });
      });

    const response = h.response({
      status: "success",
      message: "get Arabica successfully",
      data: arabica,
    });
    response.code(400);
    return response;
  } catch (error) {
    console.log(error);
    const response = h.response({
      status: "fail",
      message: "get Arabica failed: " + error,
    });
    response.code(400);
    return response;
  }
};
const getArabicaById = async (request, h) => {
  try {
    const { id } = request.params;

    const docRef = db.collection("recipes").doc(id);
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
      };

      const response = h.response({
        status: "success",
        message: "Fetched Arabica recipe by ID successfully",
        data: arabicaRecipe,
      });
      response.code(200);
      return response;
    } else {
      const response = h.response({
        status: "fail",
        message: "Arabica recipe not found",
      });
      response.code(404);
      return response;
    }
  } catch (error) {
    console.error(error);
    const response = h.response({
      status: "fail",
      message: "Failed to get Arabica recipe by ID: " + error.message,
    });
    response.code(500);
    return response;
  }
};

const addData = (request, h) => {
  const recipesCollection = db.collection("recipes");
  try {
    arabica.map((recipe,) => {
      recipesCollection
        .doc(recipe.coffeeId.toString())
        .set(recipe, { merge: true })
        .then(() => {
          console.log(`${recipe.name} recipe added to Firebase!`);
        })
        .catch((error) => {
          console.error(`Error adding ${recipe.name} recipe: `, error);
          throw new Error(error);
        });
    });
    const response = h.response({
      status: "success",
      message: "Data berhasil ditambahkan",
    });
    response.code(201);
    return response;
  } catch (error) {
    const response = h.response({
      status: "fail",
      message: `Error adding ${recipe.name} recipe: ${error}`,
    });
    response.code(400);
    return response;
  }
};

module.exports = { addData, getArabica, getArabicaById };
