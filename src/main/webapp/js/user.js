var userid;
var url;
var objectURL
function login()
{
	var data = { username: document.querySelector("#userNameLogin").value, 
		         password: document.querySelector("#passwordLogin").value 
		   };

	fetch('data/login',   {
	    method: 'post',
	    headers: {
	    	'Content-type': 'application/json' 
	    },
	    body: JSON.stringify(data)
	  })
	  .then( response => {
		    document.querySelector("#userNameLogin").value = "";
		    document.querySelector("#passwordLogin").value = "";
			if( !response.ok )
			{
				document.querySelector("#errorAccess").innerHTML = "Benutzername oder Passwort ist falsch!";
				throw Error(response.statusText);
			}
			else
			{
				document.querySelector("#errorAccess").innerHTML = "Willkommen " + data.username;
				document.querySelector("#btnlogout").disabled = false;
				document.querySelector("#btnlogin").disabled = true;
				userratings(data);
				
			}
		} )
	 .catch( error => console.error('Error:', error));
}

function logout()
{
	fetch('data/login',   {
	    method: 'delete'
	  })
	  .then( response => {
		  if( response.ok )
	      {
			  document.querySelector("#errorAccess").innerHTML = "Abgemeldet!";
				document.querySelector("#btnlogout").disabled = true;
				document.querySelector("#btnlogin").disabled = false;
			  let node = document.getElementById("myratings");
				node.innerHTML = "";
		  }  
	  } )
	  .catch( error => console.error('Error:', error) );
}


function register()
{
	let data = { username : document.querySelector("#username").value, 
			     email : document.querySelector("#email").value,
			     firstname : document.querySelector("#firstname").value, 
			     lastname : document.querySelector("#lastname").value,
			     street : document.querySelector("#street").value, 
			     streetNr : document.querySelector("#streetNr").value,
			     zip : document.querySelector("#plz").value, 
			     city : document.querySelector("#city").value,
			     password : document.querySelector("#passwd").value 

			   };

	fetch('data/register',   {
	    method: 'post',
	    headers: {
	    	'Content-type': 'application/json' 
	    },
	    body: JSON.stringify(data)
	  })
	.then( response => {
		document.querySelector("#username").value = "";
	     document.querySelector("#email").value = "";
	     document.querySelector("#firstname").value = "";
	     document.querySelector("#lastname").value = "";
	     document.querySelector("#street").value = "";
	     document.querySelector("#telefon").value = "";
	     document.querySelector("#streetNr").value = "";
	     document.querySelector("#plz").value = "";
	     document.querySelector("#city").value = "";
	     document.querySelector("#passwd").value = "";
		if( !response.ok )
		{
			document.querySelector("#errorAccess").innerHTML = "Benutzername bereits vergeben!";
			throw Error(response.statusText);
		}
		else
		{
			document.querySelector("#errorAccess").innerHTML = "Benutzer registriert";
			console.log("Registration success");
		}
	} )
	.catch( error => console.error('Error:', error));
}

function userratings(data){
	let node = document.getElementById("myratings");
	node.innerHTML = "";


	
	
	fetch('data/myrating',   {
	    method: 'post',
	    headers: {
	    	'Content-type': 'application/json' 
	    },
	    body: JSON.stringify(data)})
	   .then(response => response.json())
	   .then(rates => rates.forEach( rating => {

		   fetch('data/images',   {
			    method: 'post',
			    headers: {
			    	'Content-type': 'application/json' 
			    },
			    body: JSON.stringify(rating)})
			    .then(result => result.blob())
			    .then(resultimage =>{
		   
			    	  objectURL = URL.createObjectURL(resultimage);
		   
		   
		   
			//let val = recieveimage(rating);		    
			let row = document.createElement("tr");
			let entry1 = document.createElement("td");
			let entry2 = document.createElement("td");
			let entry3 = document.createElement("td");
			let entry4 = document.createElement("td");
			let entry41 = document.createElement("img");

			let tags = document.createTextNode(rating.createdt);
			console.log(tags);
			entry1.appendChild(tags);
			
			let grade = document.createTextNode(rating.grade + " Sterne");
			entry2.appendChild(grade);
			
			let value = document.createTextNode(rating.txt);
			entry3.appendChild(value);
			
			//let image = document.createTextNode(entry41);
			console.log(objectURL);
			entry41.setAttribute("src", objectURL);
			entry41.setAttribute("width", "100");
			entry41.setAttribute("height", "100");
			entry4.appendChild(entry41);
			
			
			
			row.appendChild(entry3);
			row.appendChild(entry1);	
			row.appendChild(entry2);
			row.appendChild(entry4);			

			node.appendChild(row);


			    })    
			}))

}


	    


