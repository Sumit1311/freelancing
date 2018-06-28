var Client = require('ssh2').Client;
var Q = require('q');


var conn, op = "";
var streams = [];
var deferred = Q.defer();
exports.startSession = startSession;
exports.sendData = sendData;
exports.endSession = endSession;

function startSession() {
    conn = new Client();
    op = '';
    deferred = Q.defer();
    conn.on('ready', function() {
        console.log('Client :: ready');
        conn.shell(function(err, stream) {
            if (err) throw err;
            streams.push(stream);
            subscribeEvents(stream);
            deferred.resolve();
            //sendData('/home/geek/workspace/freelancing/Shell-Redirector/test.sh\n');
            //stream.write('/home/geek/workspace/freelancing/Shell-Redirector/test.sh\n');
        });
    }).connect({
        host: 'localhost',
        port: 22,
        username: 'geek',
        password : '12345'
    });
    return deferred.promise;
}

function sendData(data) {
    var stream =streams[0];

    deferred=Q.defer();
    if(stream) {
        op= '';
        stream.write(data);
    } else {
        deferred.reject();
    }
    return deferred.promise;
}


function subscribeEvents(stream) {

            stream.on('close', 
                function() {
                    //console.log('Stream :: close');
                    conn.end();
                    deferred.resolve("Closed");
                })
                .on('data', 
                    function(data) {
                        //console.log('STDOUT: ' + data);
                        if(data.includes("Please enter a char")) {
                            op += data;
                            deferred.resolve(op);
                            //console.log("true");
                            //stream.write('s\n');
                        } else if(data.includes("Char is")){
                            op += data;
                            deferred.resolve(op);
                        } else {
                            op += data;
                        }

                    })
                .stderr.on('data', 
                    function(data) {
                        console.log('STDERR: ' + data);
                        deferred.reject(data);
                    });
}

function endSession() {
    return sendData("exit\n");
}
