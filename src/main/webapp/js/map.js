let mymap;
let redIcon;
let blueIcon;
let selectedMarker;
var poistore;

window.onload = function() {
	document.querySelector("#btnlogout").disabled = true;
	logout();
	mymap = L.map('mapid').setView([ 49.250723, 7.377122 ], 13);
	L
			.tileLayer(
					'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?'
							+ 'access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpe'
							+ 'jY4NXVycTA2emYycXBndHRqcmZ3N3gifQ'
							+ '.rJcFIG214AriISLbB6B5aw',
					{
						maxZoom : 18,
						attribution : 'Map data &copy; '
								+ '<a href="https://www.openstreetmap.org/">OpenStreetMap</a>'
								+ ' contributors, '
								+ '<a href="https://creativecommons.org/licenses/by-sa/2.0/">'
								+ 'CC-BY-SA</a>, '
								+ 'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
						id : 'mapbox.streets'
					}).addTo(mymap);

	redIcon = new L.Icon({
		iconUrl : './icon/marker-icon-red.png',
		shadowUrl : './icon/marker-shadow.png',
		iconSize : [ 25, 41 ],
		iconAnchor : [ 12, 41 ],
		popupAnchor : [ 1, -34 ],
		shadowSize : [ 41, 41 ]
	});

	blueIcon = new L.Icon({
		iconUrl : './icon/marker-icon-blue.png',
		shadowUrl : './icon/marker-shadow.png',
		iconSize : [ 25, 41 ],
		iconAnchor : [ 12, 41 ],
		popupAnchor : [ 1, -34 ],
		shadowSize : [ 41, 41 ]
	});
	
	showPoisOnMap ();

}

function showPoisOnMap() {
	fetch("data/poi")
	.then(response => response.json())
	.then(data => data.forEach( poi => {
		let callback = poiSelectionCallback(poi);
		L.marker([poi.lat, poi.lon], {icon: blueIcon})
		.addTo(mymap)
		.on('click', callback);
	} )
	)
	.catch( error => console.log(error) );
}

function poiSelectionCallback(poi) {
	return function(event)
	{
		poistore = poi;
		console.log(poistore.osmId);
		if (selectedMarker != null) { selectedMarker.setIcon(blueIcon);}
		let node = document.getElementById("poitag");
		node.innerHTML = "";
		
		selectedMarker = event.target;
		selectedMarker.setIcon(redIcon);
// console.log("Event" );
// console.log( event );
// console.log("Poi" );
// console.log( poi );
		poi.poiTags.forEach( item => {	
			
			if(item.tag == "name"){
				document.querySelector("#poiname").innerHTML = item.value;
			}
			let row = document.createElement("tr");
			let entry1 = document.createElement("td");
			let entry2 = document.createElement("td");

			
			let tags = document.createTextNode(item.tag);
			entry1.appendChild(tags);
			
			let value = document.createTextNode(item.value);
			entry2.appendChild(value);
			
			row.appendChild(entry1);
			row.appendChild(entry2);			

			node.appendChild(row);
		} );
		loadpoiratings(poi);
	}
}

function loadpoiratings(poi){
	document.querySelector("#rate").style.display = "block";
	let node = document.getElementById("poiratings");
	node.innerHTML = "";

	fetch('data/poi',   {
	    method: 'post',
	    headers: {
	    	'Content-type': 'application/json' 
	    },
	    body: JSON.stringify(poi)})
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
			

			   
			   let row1 = document.createElement("tr");
			   let entry1 = document.createElement("td");
			   let row2 = document.createElement("tr");
			   let entry2 = document.createElement("td");
			   let row3 = document.createElement("tr");
			   let entry3 = document.createElement("td");
			   let row4 = document.createElement("tr");
			   let entry4 = document.createElement("td");
			   let entry41 = document.createElement("img");
			   let br = document.createElement("br");
			   
			   
			   
				let grade = document.createTextNode(rating.grade + " Sterne");
				entry1.appendChild(grade);
				let date = document.createTextNode("Jemand schrieb am: " + rating.createdt);
				entry2.appendChild(date);
				let comment = document.createTextNode(rating.txt);
				entry3.appendChild(comment);
				
				// let image = document.createTextNode(entry41);
				console.log(objectURL);
				entry41.setAttribute("src", objectURL);
				entry41.setAttribute("width", "100");
				entry41.setAttribute("height", "100");
				entry4.appendChild(entry41);
				
				row1.appendChild(entry1);
				node.appendChild(row1);
				row2.appendChild(entry2);
				node.appendChild(row2);
				row3.appendChild(entry3);
				node.appendChild(row3);
				row4.appendChild(entry4);
				node.appendChild(row4);
				node.appendChild(br);
		
			    })   
			 }))

}


function showRegistration() {
	document.querySelector("#registration").style.display = "block";
}

function hideRegistration() {
	document.querySelector("#registration").style.display = "none";

}
