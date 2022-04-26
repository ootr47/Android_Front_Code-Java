const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//   functions.logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });

exports.androidPushNotification = functions.firestore
  .document("Notifications/{docId}")
  .onCreate((snapshot, context) => {
    // Subscribe Push Notifications
    // admin.messaging().sendToTopic("new_user_forums", {
    //   notification: {
    //     title: snapshot.data().title,
    //     body: snapshot.data().body,
    //   },
    // });

    // Token Push Notifications
    admin
      .firestore()
      .collection("DeviceTokens")
      .get()
      .then((result) => {
        let registrationTokens = [];
        result.docs.forEach((tokenDocument) => {
          registrationTokens.push(tokenDocument.data().token);
        });
        admin.messaging().sendMulticast({
          tokens: registrationTokens,
          notification: {
            title: snapshot.data().title,
            body: snapshot.data().body,
          },
        });
      });
  });
