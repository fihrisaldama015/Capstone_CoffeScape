const Hapi = require("@hapi/hapi");
const routes = require("./routes");
require("dotenv").config();

const init = async () => {
  const port = 9000;
  const server = Hapi.server({
    port,
    host: "localhost",
  });

  server.route(routes);
  await server.start();
  console.log(`Server running on port ${port}`);
};

init();
