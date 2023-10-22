//Expertise Back
const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const UserVerificationSchema = new Schema({
userId:String,
uniqueString:String,
createdAt: Date,
expiresAt: Date,
});

module.exports = mongoose.model("userVerification", UserVerificationSchema);