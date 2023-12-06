const { db, FieldValue } = require("../lib/firebase");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");

const getAllUsers = async (request, h) => {
  try {
    let users = [];
    await db
      .collection("users")
      .get()
      .then((querySnapshot) => {
        querySnapshot.forEach((doc) => {
          const temp = {
            id: doc.id,
            email: doc.data().email,
            name: doc.data().name,
          };
          users.push(temp);
        });
      });
    const response = h.response({
      status: "success",
      message: "get Users successfully",
      data: users,
    });
    response.code(200);
    return response;
  } catch (error) {
    console.log(error);
    const response = h.response({
      status: "fail",
      message: "get Users failed: " + error,
    });
    response.code(400);
    return response;
  }
};

const registerUser = async (request, h) => {
  try {
    if (!request.payload) {
      const response = h.response({
        status: "fail",
        message: "Please add a JSON payload",
      });
      response.code(400);
      return response;
    }

    const { email, name, password } = request.payload;

    if (!email || !name || !password) {
      const response = h.response({
        status: "fail",
        message:
          "Please fill all field in JSON, `email`, `name`, and `password`",
      });
      response.code(400);
      return response;
    }

    const userWithSameEmail = await db
      .collection("users")
      .where("email", "==", email)
      .get();

    if (!userWithSameEmail.empty) {
      const response = h.response({
        status: "fail",
        message: "Email already exist",
      });
      response.code(400);
      return response;
    }

    const saltRounds = 10;
    const hashedPassword = await bcrypt.hash(password, saltRounds);

    const data = {
      email,
      name,
      password: hashedPassword,
    };

    const user = await db.collection("users").add(data);
    const response = h.response({
      status: "success",
      message: "User created successfully",
      data: {
        id: user.id,
        email,
        name,
      },
    });
    response.code(201);
    return response;
  } catch (error) {
    console.log(error);
    const response = h.response({
      status: "fail",
      message: "User created failed: " + error.message,
    });
    response.code(400);
    return response;
  }
};

const loginUser = async (request, h) => {
  try {
    if (!request.payload) {
      const response = h.response({
        status: "fail",
        message: "Please add a valid JSON payload",
      });
      response.code(400);
      return response;
    }

    const { email, password } = request.payload;

    if (!email || !password) {
      const response = h.response({
        status: "fail",
        message: "Please fill all field in JSON, `email` and `password`",
      });
      response.code(400);
      return response;
    }

    const user = await db.collection("users").where("email", "==", email).get();

    if (user.empty) {
      const response = h.response({
        status: "fail",
        message: "User not found",
      });
      response.code(404);
      return response;
    }

    const userData = user.docs[0].data();
    const isMatch = await bcrypt.compare(password, userData.password);

    if (!isMatch) {
      const response = h.response({
        status: "fail",
        message: "Wrong password",
      });
      response.code(400);
      return response;
    }

    const JWTPayloadData = {
      id: user.docs[0].id,
      email: userData.email,
      name: userData.name,
      iss: "http://localhost",
      exp: Math.floor(Date.now() / 1000) + 60 * 60 * 24,
      iat: Math.floor(Date.now() / 1000),
    };

    const token = jwt.sign(JWTPayloadData, process.env.JWT_SECRET_KEY);

    const response = h.response({
      status: "success",
      message: "Login successfully",
      data: {
        token,
      },
    });
    response.code(200);
    return response;
  } catch (error) {
    console.log(error);
    const response = h.response({
      status: "fail",
      message: "Login failed: " + error.message,
    });
    response.code(400);
    return response;
  }
};
const updateUser = async (request, h) => {
  try {
    const { id } = request.params;
    let user = await db.collection("users").doc(id).get();
    if (!user.exists) {
      const response = h.response({
        status: "fail",
        message: "User not found",
      });
      response.code(404);
      return response;
    }
    if (!request.payload) {
      const response = h.response({
        status: "fail",
        message: "Please add payload",
      });
      response.code(400);
      return response;
    }

    let { email, name, password } = request.payload;

    if (!email || !name || !password) {
      const response = h.response({
        status: "fail",
        message: "Please fill all field, email, name, and password",
      });
      response.code(400);
      return response;
    }

    const saltRounds = 10;
    const hashedPassword = await bcrypt.hash(password, saltRounds);

    const data = {
      email,
      name,
      password: hashedPassword,
    };

    let userRef = db.collection("users").doc(id);
    await userRef.update(data);
    const response = h.response({
      status: "success",
      message: "User Updated successfully",
      data: {
        id: user.id,
        email,
        name,
      },
    });
    response.code(201);
    return response;
  } catch (error) {
    console.log(error);
    const response = h.response({
      status: "fail",
      message: "User Updated failed: " + error.message,
    });
    response.code(400);
    return response;
  }
};
const getUserById = async (request, h) => {
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
    const response = h.response({
      status: "success",
      message: "get User successfully",
      data: {
        id: user.id,
        email: user.data().email,
        name: user.data().name,
      },
    });
    response.code(200);
    return response;
  } catch (error) {
    console.log(error);
    const response = h.response({
      status: "fail",
      message: "get User failed: " + error,
    });
    response.code(400);
    return response;
  }
};

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
        favoriteCoffee: user.data().favoriteCoffee,
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
        favoriteCoffee: user.data().favoriteCoffee,
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

module.exports = {
  getAllUsers,
  registerUser,
  loginUser,
  updateUser,
  getUserById,
  addFavoriteCoffee,
  removeFavoriteCoffee,
};
