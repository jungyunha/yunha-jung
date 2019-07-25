let employee = {};

window.onload = function() {
    populateEmployee();
}

function populateEmployee() {
    // send a GET request to SessionServlet to obtain session information
    fetch("http://localhost:8082/Project1_war/session").then(function(response) {
        return response.json(); // parsing json data in the response as a JS object
    }).then(function(data) {
        console.log(data);
        // check whether there is a valid session
        //define behavior for when there is no valid user
        if(data.session === null) {
            window.location = "http://localhost:8082/Project1_war/login"
        } else {
            //define behavior for when a user is returned
            employee = data;
            document.getElementById("username").innerText = "username: "+employee.username;
            document.getElementById("firstname").innerText = "firstname: "+employee.firstname;
            document.getElementById("lastname").innerText = "lastname: "+employee.lastname;
            document.getElementById("email").innerText = "email: "+employee.email;
        }
    })
}