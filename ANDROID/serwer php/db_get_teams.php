<?php
  
// array for JSON response
$response = array();

// include db connect class
require_once '/home/a1229767/public_html/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// check for post data
//if (isset($_GET["pid"])) {
    //$pid = $_GET['pid'];
 
    // get a product from products table
    $result = mysql_query("SELECT * FROM Team");
 
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
 
            $result = mysql_fetch_array($result);
 
            $team = array();
            $team["id"] = $result["id"];
            $team["name"] = $result["name"];
            $team["owner"] = $result["owner"];
            // success
            $response["success"] = 1;
 
            // user node
            $response["team"] = array();
 
            array_push($response["team"], $team);
 
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No Team found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No table Team found in database";
 
        // echo no users JSON
        echo json_encode($response);
    }
/*} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}*/
?>