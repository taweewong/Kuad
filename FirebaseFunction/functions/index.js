const functions = require('firebase-functions');
const admin = require('firebase-admin')
admin.initializeApp(functions.config().firebase)
const ref = admin.database().ref()

exports.helloKuad = functions.https.onRequest((request, response) => {
    response.send("Hello from Kuad!");
});
