//Expertise Back
const mongoose = require("mongoose");
const Schema = mongoose.Schema;
const UserSchema = new Schema(
  {
    name: {
      type: String,
      required: true,
    },
    email: {
      type: String,
      required: true,
      unique: true,
    },
    password: {
      type: String,
      required: true,
    },
    verified: Boolean,
    resetToken: String,
    expireToken: String,
    role: {
      type: String,
      enum: ["student", "instructor", "admin"],
    },
    courses: [
      {
        type: Schema.Types.ObjectId,
        ref: "course",
      },
    ],
  },
  {
    timestamps: true,
  }
);

module.exports = mongoose.model("user", UserSchema);
