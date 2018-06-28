var request = require("request");
var url = process.argv[3];

var string = process.argv[2];

console.log("Requesting the URL :", url);
request(url, requestCallbackfunction);

function requestCallbackfunction (error, response, body) {
          if(error) {
            console.log('error:', error, 'for URL : ', url); // Print the error if one occurred
            return;
          }
          console.log('statusCode:', response && response.statusCode); // Print the response status code if a response was received
          //console.log('body:', body); // Print the HTML for the Google homepage.
          var index = body.indexOf(string);
          if(index == -1) {
            console.log("String not found");
          } else {
            console.log("String Found");
          } 
}
