const Hapi = require("@hapi/hapi");
const routes = require("./routes");
require("dotenv").config();

const init = async () => {
  const port = 9000;
  const server = Hapi.server({
    port,
    host: "0.0.0.0",
  });

  server.route(routes);

  server.route({
    method: "GET",
    path: "/",
    handler: (request, h) => {
      const response = h.response({
        status: "success",
        name: "CoffeeScape API - V1",
        documentation:
          "https://documenter.getpostman.com/view/21791853/2s9YeD9tAT",
        message:
          "Welcome to CoffeeScape API, you can read the documentation above to get started",
      });
      response.code(200);
      return response;
    },
  });

  await server.start();
  console.log(`Server running on port ${port}`);
};

init();
