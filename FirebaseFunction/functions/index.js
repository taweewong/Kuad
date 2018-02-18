const functions = require('firebase-functions');
const admin = require('firebase-admin')
admin.initializeApp(functions.config().firebase)
const ref = admin.database().ref()

exports.helloKuad = functions.https.onRequest((request, response) => {
    response.send("Hello from Kuad!");
});

exports.randomBottle = functions.https.onRequest((request, response) => {
    ref.child('bottles').orderByChild('isHolding').equalTo(false).once('value')
    .then(snap => {
        response.send(snap)
        return snap
    }).catch(error => {
        console.log(error)
        response.send(error)
    })
});
