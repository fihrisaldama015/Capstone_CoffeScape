const arabica = require("../rating");
const { db, FieldValue } = require("../lib/firebase");

const addRating = async (request, h) => {
  const ratingsCollection = db.collection("rating");
  const recipesCollection = db.collection("recipes");

  try {
    arabica.map((rating) => {
      ratingsCollection
        .doc(rating.name)
        .set({ name: rating.name }, { merge: true })
        .then(() => {
          console.log(`${rating.name} rating added to Firebase!`);
        })
        .catch((error) => {
          console.error(`Error adding ${rating.name} rating: `, error);
          throw new Error(error);
        });
      rating.rating.forEach((item) => {
        ratingsCollection.doc(rating.name).set(
          {
            rating: FieldValue.arrayUnion(item),
          },
          { merge: true }
        );
      });

      const ratingArray = rating.rating;
      let ratingLength = 0;
      let ratingSum = 0;
      ratingArray.forEach((item) => {
        if (item.rating > 0) {
          ratingSum += Number(item.rating);
          ratingLength++;
        }
      });
      const average = ratingSum / ratingLength;

      recipesCollection.doc(rating.name).set(
        {
          rating: average,
        },
        { merge: true }
      );
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
      message: `Error adding ${rating.name} rating: ${error}`,
    });
    response.code(400);
    return response;
  }
};

const addCoffeeRating = async (request, h) => {
  try {
    const { id } = request.params;
    const { coffeeId, rating } = request.payload;

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

    if (rating > 10 || rating < 1) {
      const response = h.response({
        status: "fail",
        message: "Please Input Rating between 1 - 10",
      });
      response.code(404);
      return response;
    }

    const ratingRef = db.collection("rating").doc(coffeeId);
    const allRating = await ratingRef.get();

    const ratingExist = allRating
      .data()
      .rating.find((item) => item.userId === id);
    if (ratingExist) {
      const response = h.response({
        status: "fail",
        message: `This user already give rating to this recipe with id: ${coffeeId}`,
      });
      response.code(400);
      return response;
    }

    await ratingRef.set(
      {
        rating: FieldValue.arrayUnion({ userId: id, rating }),
      },
      { merge: true }
    );

    const ratingArray = allRating.data().rating;
    let ratingLength = 0;
    ratingArray.forEach((rating) => {
      if (rating.rating > 0) {
        ratingLength++;
      }
    });
    const ratingSum = ratingArray.reduce((acc, curr) => {
      return acc + Number(curr.rating);
    }, 0);
    const average = (ratingSum + Number(rating)) / (ratingLength + 1);

    const recipesCollection = db.collection("recipes");

    recipesCollection.doc(coffeeId).set(
      {
        rating: average,
      },
      { merge: true }
    );

    const response = h.response({
      status: "success",
      message: "add rating successfully to recipe with id: " + coffeeId,
      data: {
        id: user.id,
        rating: rating,
      },
    });
    response.code(201);
    return response;
  } catch (error) {
    console.log(error);
    const response = h.response({
      status: "fail",
      message: "add rating failed: " + error,
    });
    response.code(400);
    return response;
  }
};

const removeRatingCoffee = async (request, h) => {
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

    const ratingRef = db.collection("rating").doc(coffeeId);
    const allRating = await ratingRef.get();

    const ratingExist = allRating
      .data()
      .rating.find((item) => item.userId === id);

    if (!ratingExist) {
      const response = h.response({
        status: "fail",
        message: `This user is not giving rating to recipe with id: ${coffeeId}`,
      });
      response.code(400);
      return response;
    }

    const ratingIndex = allRating
      .data()
      .rating.findIndex((item) => item.userId === id);

    await ratingRef.update({
      rating: FieldValue.arrayRemove(allRating.data().rating[ratingIndex]),
    });

    const allRatingAfterDelete = await ratingRef.get();
    const ratingArray = allRatingAfterDelete.data().rating;
    let ratingLength = 0;
    ratingArray.forEach((rating) => {
      if (rating.rating > 0) {
        ratingLength++;
      }
    });
    const ratingSum = ratingArray.reduce((acc, curr) => {
      return acc + Number(curr.rating);
    }, 0);
    const average = ratingSum / ratingLength;

    const recipesCollection = db.collection("recipes");

    recipesCollection.doc(coffeeId).set(
      {
        rating: average,
      },
      { merge: true }
    );

    const response = h.response({
      status: "success",
      message: "remove rating successfully to recipe with id: " + coffeeId,
      data: {
        id: user.id,
      },
    });
    response.code(201);
    return response;
  } catch (error) {
    console.log(
      "🚀 ~ file: userRatingHandler.js:246 ~ removeRatingCoffee ~ error:",
      error
    );
    const response = h.response({
      status: "fail",
      message: "remove favorite failed: " + error,
    });
    response.code(400);
    return response;
  }
};

module.exports = { addRating, addCoffeeRating, removeRatingCoffee };
