const jwt = require("jsonwebtoken");
const User = require("../model/userModel");


const auth = (req, res, next) => {
  try {
    const authHeader = req.headers.authorization;
    if (authHeader && authHeader.startsWith("Bearer ")) {
      const token = authHeader.substring(7);
      const decodedToken = jwt.verify(token, "verySecretValue");
      const userId = decodedToken._id;
      console.log(decodedToken);
      req.auth = {
        userId: userId,
      };
      next();
    } else {
      throw "Authorization token is missing or invalid";
    }
  } catch (error) {
    console.log(error);
    res.status(401).json({ error });
  }
};

const signAccessToken = (userId) => {
  return new Promise((resolve, reject) => {
    const payload = { _id: userId };
    const secret = "verySecretValue";
    const options = {
      audience: userId,
    };
    jwt.sign(payload, secret, options, (err, token) => {
      if (err) {
        console.log(err.message);
        reject(createError.InternalServerError());
        return;
      }
      resolve(token);
    });
  });
};




module.exports = { auth, signAccessToken };
