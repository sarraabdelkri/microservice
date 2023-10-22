const express = require("express");
const cors = require("cors");
const cookieSession = require("cookie-session");
const app = express();
const passportSetup = require("./passport");
const passport = require("passport");
const authRoute = require("./route/authgo");
const mongoose = require("mongoose");
const userRouter = require("./route/userRoute");
const session = require("express-session");
const dotenv = require("dotenv");
app.use(
  cookieSession({ name: "session", keys: ["lama"], maxAge: 24 * 60 * 60 * 100 })
);

app.use(passport.initialize());
app.use(passport.session());

app.use(
  cors({
    origin: "http://localhost:3000",
    methods: "GET,POST,PUT,DELETE",
    credentials: true,
  })
);

app.use("/auth", authRoute);

app.listen("5000", () => {
  console.log("Server is running!");
});
dotenv.config();

// configure session middleware
app.use(
  session({
    secret: "abc1234",
    resave: false,
    saveUninitialized: true,
    cookie: { secure: false },
  })
);

app.use(cors());

app.use(express.urlencoded({ extended: true }));

//connecting to db
mongoose.set("strictQuery", false);
mongoose
  .connect(
    "mongodb+srv://inesmslm:30101999@cluster0.akykbui.mongodb.net/?retryWrites=true&w=majority"
  )
  .then(() => console.log("CONNECTED TO DB"))
  .then(() => app.listen(9000))
  .catch((err) => console.log(err));

app.use(express.json());

app.use("/user", userRouter);
