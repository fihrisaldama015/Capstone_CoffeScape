const { default: axios } = require("axios");
const { db } = require("../lib/firebase");

const getCoffeeRecommendation = async (request, h) => {
  const userCollection = db.collection("users");
  try {
    const { id } = request.params;

    const user = await userCollection.doc(id).get();

    if (!user.exists) {
      const response = h.response({
        status: "fail",
        message: "User not found",
      });
      response.code(404);
      return response;
    }

    const { data: userData } = await axios.get(
      `${process.env.APP_URL}/users/${id}/rating`
    );
    const userRating = userData.data.ratingCoffee.map((item) => ({
      coffeeId: item.coffeeId,
      rating: item.rating,
    }));

    const apiResponse = await axios.post(
      `${process.env.ML_API_ENDPOINT}/recommend`,
      {
        data: userRating,
      }
    );

    const response = h.response({
      status: "success",
      message: "Prediction success",
      data: apiResponse.data,
    });
    response.code(200);
    return response;
  } catch (error) {
    console.log(
      "🚀 ~ file: predictHandler.js:43 ~ getCoffeeRecommendation ~ error:",
      error
    );
    const response = h.response({
      status: "fail",
      message: "Prediction failed: " + error.message,
    });
    response.code(400);
    return response;
  }
};

module.exports = { getCoffeeRecommendation };
