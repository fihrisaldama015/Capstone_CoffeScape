const coffee = require("../coffee");
const { db } = require("../lib/firebase");

const getcoffee = async (request, h) => {
  try {
    let coffee = [];

    await db
      .collection("recipes")
      .get()
      .then((querySnapshot) => {
        querySnapshot.forEach((doc) => {
          const temp = {
            id: doc.id,
            thumbnail: doc.data().thumbnail,
            name: doc.data().name,
            FlavorProfiles: doc.data().FlavorProfiles,
            RoastLevel: doc.data().RoastLevel,
            ServingStyle: doc.data().ServingStyle,
            RecommendedBeans: doc.data().RecommendedBeans,
            BrewingMethod: doc.data().BrewingMethod,
            moodType: doc.data().moodType,
            rating: doc.data().rating,
          };
          coffee.push(temp);
        });
      });

    if (request.query.name) {
      coffee = coffee.filter((item) => {
        const regex = new RegExp(request.query.name, "gi");
        return item.name.match(regex);
      });
    }

    if (request.query.FlavorProfiles) {
      coffee = coffee.filter((item) => {
        if (item.FlavorProfiles === undefined) return false;
        const contains = item.FlavorProfiles.includes(
          request.query.FlavorProfiles
        );
        return contains;
      });
    }

    const response = h.response({
      status: "success",
      message: "get coffee successfully",
      data: coffee,
    });
    response.code(200);
    return response;
  } catch (error) {
    console.log(error);
    const response = h.response({
      status: "fail",
      message: "get coffee failed: " + error,
    });
    response.code(400);
    return response;
  }
};
const getcoffeeById = async (request, h) => {
  try {
    const { id } = request.params;

    const docRef = db.collection("recipes").doc(id);
    const doc = await docRef.get();
    if (doc.exists) {
      const data = doc.data();
      const coffeeRecipe = {
        id: doc.id,
        thumbnail: data.thumbnail,
        name: data.name,
        FlavorProfiles: data.FlavorProfiles,
        RoastLevel: data.RoastLevel,
        ServingStyle: data.ServingStyle,
        RecommendedBeans: data.RecommendedBeans,
        BrewingMethod: data.BrewingMethod,
        moodType: doc.data().moodType,
        rating: data.rating,
      };

      const response = h.response({
        status: "success",
        message: "Fetched coffee recipe by ID successfully",
        data: coffeeRecipe,
      });
      response.code(200);
      return response;
    } else {
      const response = h.response({
        status: "fail",
        message: "coffee recipe not found",
      });
      response.code(404);
      return response;
    }
  } catch (error) {
    console.error(error);
    const response = h.response({
      status: "fail",
      message: "Failed to get coffee recipe by ID: " + error.message,
    });
    response.code(500);
    return response;
  }
};

const addData = (request, h) => {
  const recipesCollection = db.collection("recipes");
  try {
    coffee.map((recipe) => {
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

module.exports = { addData, getcoffee, getcoffeeById };
