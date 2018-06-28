var shell = require('./shell.js');

shell.startSession()
.then(function(){
    //console.log("Step 1 : ", op);
    return shell.sendData('/home/geek/workspace/freelancing/Shell-Redirector/test.sh\n');
})
.then(function(op) {
    console.log("========Sending Command======\n", op);
    return shell.sendData('s\n');
})
.then(function(op) {
    console.log("==========Sending Input=======\n", op);
    return shell.endSession();
})
.then(function(op) {
    console.log("==========Closing========\n", op);
});

