var imgpath;

function showmoreinfo(){
	document.querySelector("#poitag").style.display = "block";
	document.querySelector("#rate").style.display = "none";
}

function hidemoreinfo(){
	document.querySelector("#poitag").style.display = "none";
	document.querySelector("#rate").style.display = "block";
}

function addRating(){
	// Create a unique filename for the image to be saved on the Database
	// Pictures in .png only right now
	// in addrating ONLY the filepath will be sent to the server.
	// at the end addImage() is called to send binary picture
	fetch('data/login')
	  .then( response => response.json())
	  .then(rate => {

			  console.log(rate); // userid
			  let star = getRadioVal();
			  console.log("addRating Imgpath: " + imgpath);
			  
			  let addrating ={userid: rate,
					  		osmid: poistore.osmId,
					  		ratingtype: "txt",
					  		grade: star,
					  		txt: document.querySelector("#content").value,
					  		imagepath: imgpath};
			  
			  console.log(addrating);
			  fetch('data/poirating',   {
				    method: 'post',
				    headers: {
				    	'Content-type': 'application/json' 
				    },
				    body: JSON.stringify(addrating)})
				   .then(result => {
					 if(!result.ok){
						 throw Error(result.statusText);
					 }
					 else{
						// addImage();
						 console.log("Rate success");
					 }				   
				   })
		  }).catch( error => console.error('Error:', error) );

	
}

function getRadioVal() {
	if (document.getElementById("star1").checked  == true){
		return document.querySelector("#star1").value;
	} 
	if (document.getElementById("star2").checked  == true){
		return document.querySelector("#star2").value;
	} 
	if (document.getElementById("star3").checked  == true){
		return document.querySelector("#star3").value;
	} 
	if (document.getElementById("star4").checked  == true){
		return document.querySelector("#star4 ").value;
	} 
	if (document.getElementById("star5").checked  == true){
		return document.querySelector("#star5").value;
	} 
}

function addImage(){
	// Bild-Datei wird im Binärformat zu Server übertragen
	// Server übergibt eindeutigen Dateinnamen.
	let image = document.getElementById("fileUpload");
	let file = image.files[0];

	fetch('data/images',   {
	    method: 'put',
	    headers: {
	    	'Content-type': 'image/png' 
	    },
	    body: file})
	    .then(response => response.text())
	    .then(filepath => {	    	
	    	imgpath  = filepath;
	    	console.log("filepath: " + filepath);
	    	})
}