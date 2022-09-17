

function mostrar() {
    alert("hola");
    var mysql = require('mysql');
    var con = mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "basededatos2.0"
    });

    con.connect(function (err) {
        if (err) throw err;
        console.log("Connected!");

    });
    con.end();
    alert("hola2");

}
    

