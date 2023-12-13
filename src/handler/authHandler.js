const jwt = require("jsonwebtoken");
const { db } = require("../lib/firebase");
const bcrypt = require("bcrypt");

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
    console.log("🚀 ~ file: authHandler.js:63 ~ registerUser ~ error:", error);
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
        message: "User not found with email: " + email,
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
        userId: user.docs[0].id,
        token,
      },
    });
    response.code(200);
    return response;
  } catch (error) {
    console.log("🚀 ~ file: authHandler.js:142 ~ loginUser ~ error:", error)
    const response = h.response({
      status: "fail",
      message: "Login failed: " + error.message,
    });
    response.code(400);
    return response;
  }
};

module.exports = { registerUser, loginUser };
