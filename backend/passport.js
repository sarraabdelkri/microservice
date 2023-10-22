const GoogleStrategy = require("passport-google-oauth20").Strategy;
const GithubStrategy = require("passport-github2").Strategy;
const FacebookStrategy = require("passport-facebook").Strategy;
const passport = require("passport");
const GOOGLE_CLIENT_ID ="737803146083-f67j2eoco2m8apn2d8hcqr2eagug6f6q.apps.googleusercontent.com";
const GOOGLE_CLIENT_SECRET = "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";

 GITHUB_CLIENT_ID = "db6eaf08447f4936bed9";
 GITHUB_CLIENT_SECRET = "51e6de08efbfe6b00c89c5b0dfe01b574627c9b1";

// FACEBOOK_APP_ID = "your id";
// FACEBOOK_APP_SECRET = "your id";

passport.use(
  new GoogleStrategy(
    {
      clientID: GOOGLE_CLIENT_ID,
      clientSecret: GOOGLE_CLIENT_SECRET,
      callbackURL: "/auth/google/callback",
    },
    function (accessToken, refreshToken, profile, done) {
      done(null, profile);
    }
  )
);

 passport.use(
   new GithubStrategy(
     {
       clientID: GITHUB_CLIENT_ID,
       clientSecret: GITHUB_CLIENT_SECRET,
       callbackURL: "/auth/github/callback",
     },
     function (accessToken, refreshToken, profile, done) {
       done(null, profile);
     }
   )
 );

// passport.use(
//   new FacebookStrategy(
//     {
//       clientID: FACEBOOK_APP_ID,
//       clientSecret: FACEBOOK_APP_SECRET,
//       callbackURL: "/auth/facebook/callback",
//     },
//     function (accessToken, refreshToken, profile, done) {
//       done(null, profile);
//     }
//   )
// );

passport.serializeUser((user, done) => {
  done(null, user);
});

passport.deserializeUser((user, done) => {
  done(null, user);
});
