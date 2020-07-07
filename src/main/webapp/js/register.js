var firstnameOk = false;
var lastnameOk = false;
var plzOk = false;
var telefonOk = false;
var emailOk = false;
var usernameOk = false;
var passOk = false;
var canvas = document.querySelector("#pwdCanvas");
var ctx = canvas.getContext("2d");

ctx.fillStyle = "white";
ctx.fillRect(0, 0, 80, 10);

function checkIfAllOk() {
	let allOk = firstnameOk && lastnameOk && plzOk && telefonOk && emailOk
			&& usernameOk && passOk;
	if (allOk) {
		document.querySelector("#btn").disabled = false;
	} else {
		document.querySelector("#btn").disabled = true;
	}
}

function checkFirstnameOnFocus() {
	document.querySelector("#errorFirstname").innerHTML = "";
}

function checkFirstnameOnBlur() {
	if (document.querySelector("#firstname").value.length == 0)
		return;

	if (firstnameOk == false) {
		document.querySelector("#errorFirstname").innerHTML = "Format des Vornamens ist falsch";
	}
}

function checkFirstname() {
	let nameInput = document.querySelector("#firstname").value;
	if (nameInput.length === 0)
		return;

	let nameRegEx = /^[A-ZÄÖÜ][a-zäöüß]+(-[A-ZÄÖÜ][a-zäöüß]+)*$/;
	if (nameRegEx.test(nameInput) == false) {
		firstnameOk = false;
	} else {
		firstnameOk = true;
	}
	checkIfAllOk();
}

function checkLastnameOnFocus() {
	document.querySelector("#errorLastname").innerHTML = "";
}

function checkLastnameOnBlur() {
	if (document.querySelector("#lastname").value.length == 0)
		return;

	if (lastnameOk == false) {
		document.querySelector("#errorLastname").innerHTML = "Format des Nachnamens ist falsch";
	}
}

function checkLastname() {
	let nameInput = document.querySelector("#lastname").value;
	if (nameInput.length === 0)
		return;

	let nameRegEx = /^[A-ZÄÖÜ][a-zäöüß]+(-[A-ZÄÖÜ][a-zäöüß]+)*$/;
	if (nameRegEx.test(nameInput) == false) {
		lastnameOk = false;
	} else {
		lastnameOk = true;
	}
	checkIfAllOk();
}

function checkPlzOnFocus() {
	document.querySelector("#errorPlz").innerHTML = "";
}

function checkPlzOnBlur() {
	if (document.querySelector("#plz").value.length == 0)
		return;

	if (plzOk == false) {
		document.querySelector("#errorPlz").innerHTML = "Format PLZ falsch.";
	}
	
	
	if(plzOk == true){
	let nameInput = document.querySelector("#plz").value;
	let url = 'http://escher.informatik.hs-kl.de/PlzService/ort?plz=' + nameInput;
	
	fetch(url, {
	method: 'get',
	headers: {
	'Accept': 'application/json'
	}
	})
	.then( response => response.json())
	.then(data => data.forEach( element => {
		document.querySelector("#errorPlz").innerHTML = data[0].ort;
	}))
	}
}


function checkPlz() {
	let nameInput = document.querySelector("#plz").value;

	if (nameInput.length === 0)
		return;

	let nameRegEx = /^\d{1,}[1-9]{4,}$/;
	if (nameRegEx.test(nameInput) == false) {
		plzOk = false;
	} else {
		plzOk = true;
	}
	checkIfAllOk();
}

function checkTelefonOnFocus() {
	document.querySelector("#errorTelefon").innerHTML = "";
}

function checkTelefonOnBlur() {
	if (document.querySelector("#telefon").value.length == 0)
		return;

	if (telefonOk == false) {
		document.querySelector("#errorTelefon").innerHTML = "Format der Telefonnummer ist falsch";
	}
}

function checkTelefon() {
	telefonOk = false;

	let telInput = document.querySelector("#telefon").value;
	if (telInput.length === 0)
		return;

	let telRegEx = /^0\d{4,}-[1-9]\d{4,}$/;
	if (telRegEx.test(telInput) == false) {
		telefonOk = false;
	} else {
		telefonOk = true;
	}
	checkIfAllOk();
}

function checkEmailOnFocus() {
	document.querySelector("#errorEmail").innerHTML = "";
}

function checkEmailOnBlur() {
	if (document.querySelector("#email").value.length == 0)
		return;

	if (emailOk == false) {
		document.querySelector("#errorEmail").innerHTML = "Format der E-Mail-Adresse ist falsch";
	}
}

function checkEmail() {
	emailOk = false;

	let emailInput = document.querySelector("#email").value;
	if (emailInput.length === 0)
		return;

	let emailRegEx = /^\w{4}\d{4,}@stud\.(hs-kl|fh-kl)\.de$/;
	if (emailRegEx.test(emailInput) == false) {
		emailOk = false;
	} else {
		emailOk = true;
	}

	checkIfAllOk();
}

function checkUsernameOnFocus() {
	document.querySelector("#errorUsername").innerHTML = "";
}

function checkUsernameOnBlur() {
	if (document.querySelector("#username").value.length == 0)
		return;

	if (usernameOk == false) {
		document.querySelector("#errorUsername").innerHTML = "Fehler beim Benutzername";
	}
}

function checkUsername() {
	usernameOk = false;

	let usernameInput = document.querySelector("#username").value;
	if (usernameInput.length === 0)
		return;

	let usernameRegEx = /^\S*$/;
	if (usernameRegEx.test(usernameInput) == false) {
		usernameOk = false;
	} else {
		usernameOk = true;
	}

	checkIfAllOk();
}

function checkPassword() {
	var passwd = document.querySelector("#passwd").value;

	var len = passwd.length;
	var regExHasTwoNumber = /\d\D+\d/;
	var regExHasSpecialSign = /[!§$&?]/;

	var hasMinLen = (len > 5);
	var hasTwoNumber = regExHasTwoNumber.test(passwd);
	var hasSpecialSign = regExHasSpecialSign.test(passwd);

	if (hasMinLen && hasTwoNumber && hasSpecialSign) {
		passOk = true;
		document.querySelector("#passwdMsg").innerHTML = "sehr sicher";

	} else if (hasMinLen && hasSpecialSign) {
		passOk = true;
		document.querySelector("#passwdMsg").innerHTML = "sicher";

	} else if (hasMinLen) {
		passOk = true;
		document.querySelector("#passwdMsg").innerHTML = "akzeptabel";

	} else {
		passOk = false;
		document.querySelector("#passwdMsg").innerHTML = "unsicher";

	}
	checkIfAllOk();

	var size = 0;
	if (hasMinLen)
		size += 4;
	if (hasTwoNumber)
		size += 4;
	if (hasSpecialSign)
		size += 4;

	var c = document.querySelector("#pwdCanvas");
	var ctx = c.getContext("2d");

	ctx.fillStyle = "red";
	ctx.fillRect(0, 0, 80, 10);

	var grd = ctx.createLinearGradient(0, 0, size * 20, 0);
	grd.addColorStop(0, "green");
	grd.addColorStop(1, "red");

	ctx.fillStyle = grd;
	ctx.fillRect(0, 0, 80, 10);
}