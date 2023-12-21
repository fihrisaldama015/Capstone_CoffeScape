const arabica = require("../rating");
const { db, FieldValue } = require("../lib/firebase");
const { default: axios } = require("axios");

const addRating = async (request, h) => {
  const ratingsCollection = db.collection("rating");
  const recipesCollection = db.collection("recipes");

  try {
    arabica.map((rating) => {
      ratingsCollection
        .doc(rating.coffeeId.toString())
        .set(rating, { merge: true })
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

      recipesCollection.doc(rating.coffeeId.toString()).set(
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
      message: `Error adding  rating: ${error}`,
    });
    response.code(400);
    return response;
  }
};

const addCoffeeRating = async (request, h) => {
  try {
    const { id } = request.params;
    const { coffeeId, rating, comment } = request.payload;

    if (!coffeeId || !rating || !comment) {
      const response = h.response({
        status: "fail",
        message: "Please fill all field, `coffeeId`, `rating`, `comment`",
      });
      response.code(400);
      return response;
    }
    try {
      const apiResponse = await axios.post(
        `${process.env.ML_API_ENDPOINT}/predict`,
        {
          text: comment,
        }
      );
      const prediction = await apiResponse.data.prediction;
      if (prediction && prediction.length > 0) {
        let reason = prediction.join(", ");
        const response = h.response({
          status: "fail",
          message: "The comment is containing bad words : " + reason,
        });
        response.code(400);
        return response;
      }
    } catch (error) {
      console.log(
        "🚀 ~ file: userRatingHandler.js:95 ~ addCoffeeRating ~ error: ML API error - ",
        error.message
      );
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
        rating: FieldValue.arrayUnion({ userId: id, rating, comment }),
      },
      { merge: true }
    );

    const userRef = db.collection("users").doc(id);

    await userRef.set(
      {
        ratingCoffee: FieldValue.arrayUnion({ coffeeId, rating, comment }),
      },
      { merge: true }
    );

    const ratingArray = allRating.data().rating;
    let ratingLength = 0;
    ratingArray.forEach((rating) => {
      if (rating.rating && rating.rating > 0) {
        ratingLength++;
      }
    });
    const ratingSum = ratingArray.reduce((acc, curr) => {
      if (curr.rating && curr.rating > 0) {
        return acc + Number(curr.rating);
      }
      return acc + 0;
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
        comment: comment,
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

    const userRef = db.collection("users").doc(id);

    const userRatingIndex = await user
      .data()
      .ratingCoffee.findIndex((item) => item.coffeeId === coffeeId);

    await userRef.update({
      ratingCoffee: FieldValue.arrayRemove(
        user.data().ratingCoffee[userRatingIndex]
      ),
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

const getUserRating = async (request, h) => {
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

    if (!user.data().ratingCoffee) {
      const response = h.response({
        status: "success",
        message: "get user rating successfully",
        data: {
          id: user.id,
          ratingCoffee: [],
        },
      });
      response.code(200);
      return response;
    }

    const userRating = user.data().ratingCoffee;
    const response = h.response({
      status: "success",
      message: "get user rating successfully",
      data: {
        id: user.id,
        ratingCoffee: userRating,
      },
    });
    response.code(200);
    return response;
  } catch (error) {
    console.log(
      "🚀 ~ file: userRatingHandler.js:246 ~ removeRatingCoffee ~ error:",
      error
    );
    const response = h.response({
      status: "fail",
      message: "get user rating failed: " + error,
    });
    response.code(400);
    return response;
  }
};

module.exports = {
  addRating,
  addCoffeeRating,
  removeRatingCoffee,
  getUserRating,
};