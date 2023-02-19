var http = require("http");
var express = require('express');
var app = express();
var mysql      = require('mysql');
var bodyParser = require('body-parser');
//dotenv.config({path: `${__dirname}/.env`})
// https://www.c-sharpcorner.com/article/integrate-swagger-open-api-with-node-express/
// had to add yarn add swagger-jsdoc and yarn add swagger-ui-express for them to run
const swaggerJSDoc = require('swagger-jsdoc');
const swaggerUI = require('swagger-ui-express');

//start mysql connection
var connection = mysql.createConnection({
  host     : 'localhost', //mysql database host name
  user     : 'root', //mysql database user name
  password : 'Developer123', //mysql database password
  database : 'kic' //mysql database name
});

connection.connect(function(err) {
  if (err) throw err
  console.log('You are now connected with mysql database...')
})
//end mysql connection

//start body-parser configuration
app.use( bodyParser.json() );       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
  extended: true
}));
//end body-parser configuration

//create app server
var server = app.listen(8400,  "0.0.0.0", function () {

  var host = server.address().address
  var port = server.address().port

  console.log("DataSynthesis DataTier API listening at http://%s:%s", host, port)
});

// Added for Swagger Options
const swaggerOptions = {
    swaggerDefinition: {
        info: {
            title:'KIC - Platform Data API',
            version:'2.0.0'
        }
    },
    apis:['index.js'],
}
const swaggerDocs = swaggerJSDoc(swaggerOptions);
app.use('/api-docs',swaggerUI.serve,swaggerUI.setup(swaggerDocs));

/*
*     APIs - Insight
*/

app.get('/dataexisting_ababanking', function (req, res) {
    connection.query('select * from insight where StatusID=1', function (error, results, fields) {
        if (error) throw error;
        res.end(JSON.stringify(results));
        res.status(200).send();
    });
});

app.get('/dataexisting_areacode', function (req, res) {
    connection.query('select * from dataexisting_areacode where StatusID=1', function (error, results, fields) {
        if (error) throw error;
        res.end(JSON.stringify(results));

    });
});

/*
app.get('/termscodesetumls/:sabcode', function (req, res) {
    connection.query('select * from terms_umls_mrconoso where StatusID=1 and SAB=?', [req.params.sabcode], function (error, results, fields) {
        if (error) throw error;
        res.end(JSON.stringify(results));
    });
});
/*

 */
/*
*    Base Code Prototyped
*/
//rest api to get all customers
/*app.get('/customer', function (req, res) {
    connection.query('select * from customer', function (error, results, fields) {
        if (error) throw error;
        res.end(JSON.stringify(results));
    });
});*/
//rest api to get a single customer data
/*app.get('/customer/:id', function (req, res) {
    connection.query('select * from customer where Id=?', [req.params.id], function (error, results, fields) {
        if (error) throw error;
        res.end(JSON.stringify(results));
    });
});*/

//rest api to create a new customer record into mysql database
/*app.post('/customer', function (req, res) {
    var params  = req.body;
    console.log(params);
    connection.query('INSERT INTO customer SET ?', params, function (error, results, fields) {
        if (error) throw error;
        res.end(JSON.stringify(results));
    });
});*/

//rest api to update record into mysql database
/*app.put('/customer', function (req, res) {
    connection.query('UPDATE `customer` SET `Name`=?,`Address`=?,`Country`=?,`Phone`=? where `Id`=?', [req.body.Name,req.body.Address, req.body.Country, req.body.Phone, req.body.Id], function (error, results, fields) {
        if (error) throw error;
        res.end(JSON.stringify(results));
    });
});*/

//rest api to delete record from mysql database
/*app.delete('/customer', function (req, res) {
    console.log(req.body);
    connection.query('DELETE FROM `customer` WHERE `Id`=?', [req.body.Id], function (error, results, fields) {
        if (error) throw error;
        res.end('Record has been deleted!');
    })
});;*/