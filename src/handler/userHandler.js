const { db } = require("../lib/firebase");
const bcrypt = require("bcrypt");

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
        favoriteCoffee: user.data().favoriteCoffee
          ? user.data().favoriteCoffee
          : [],
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

const deleteUser = async (request, h) => {
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
    await db.collection("users").doc(id).delete();
    const response = h.response({
      status: "success",
      message: "User deleted successfully",
    });
    response.code(200);
    return response;
  } catch (error) {
    console.log("🚀 ~ file: userHandler.js:161 ~ deleteUser ~ error:", error);
    const response = h.response({
      status: "fail",
      message: "User deleted failed: " + error.message,
    });
    response.code(400);
    return response;
  }
};

const forgotPassword = async (request, h) => {
  try {
    const { email, password } = request.payload;
    if (!email) {
      const response = h.response({
        status: "fail",
        message: "Please fill `email` field",
      });
      response.code(400);
      return response;
    }
    const user = await db.collection("users").where("email", "==", email).get();
    if (user.empty) {
      const response = h.response({
        status: "fail",
        message: "User not found with email: " + email,
      });
      response.code(404);
      return response;
    }
    const saltRounds = 10;
    const hashedPassword = await bcrypt.hash(password, saltRounds);
    const data = {
      password: hashedPassword,
    };
    let userRef = db.collection("users").doc(user.docs[0].id);
    await userRef.set(data, { merge: true });

    const response = h.response({
      status: "success",
      message: "User password updated successfully",
    });
    response.code(200);
    return response;
  } catch (error) {
    console.log(
      "🚀 ~ file: userHandler.js:197 ~ forgotPassword ~ error:",
      error
    );
    const response = h.response({
      status: "fail",
      message: "User found failed: " + error.message,
    });
    response.code(400);
    return response;
  }
};

module.exports = {
  getAllUsers,
  getUserById,
  updateUser,
  deleteUser,
  forgotPassword,
};
