function fino() {
	var file = "";
	if ($('#num').val() > 6) {
		$('#num').val(1);
		$('#videoLlamado').prop('volume', 0.70);
		setTimeout(writeData, 3000);
		return;
	} else {
		$('#videoLlamado').prop('volume', 0.08);
		file = setArchivo();
		if (file != "") {
			$('#elaudio').attr("src", file);
			$('#elaudio').trigger("play");
		} else {
			fino();
		}
	}
}

function load() {
	fino();
}


function setArchivo() {
	var stage = parseInt($('#num').val());
	if (stage == 1) {
		stage = stage + 1;
		$('#num').val(stage)
	}
	var n = "";
	var le = "";
	var patho = "/colas/sonido/";
	switch (stage) {
		case 1:
			n = patho + "contr.mp3";
			break;
		case 2:
			le = $('#leti').val();
			if (le == "")
				n = "";
			else
				n = patho + le + ".mp3";
			break;
		case 3:
			le = $('#nuti').val();
			if (le == "")
				n = "";
			else
				n = patho + le + ".mp3";
			break;
		case 4:
			n = patho + "escri.mp3";
			break;
		//case 5:
		//    n="es.mp3";
		//	break;
		case 5:
			le = $('#lees').val();
			if (le == "")
				n = "";
			else
				n = patho + le + ".mp3";
			break;
		case 6:
			le = $('#nues').val();
			if (le == "")
				n = "";
			else
				n = patho + le + ".mp3";
			break;

	}
	stage = stage + 1;
	$('#num').val(stage)
	return n;
}
function getSound(contri, escri) {
	var a = contri + "";
	var regvarl = /[A-Z]/g
	var regvarn = /[0-9]/g
	var resl = a.match(regvarl);
	var resn = a.match(regvarn);
	var N = "";
	if (resl != null) {
		$('#leti').val(resl[0]);
	} else $('#leti').val("");
	if (resn != null) {
		for (i = 0; i < resn.length; i++)
			N = N + resn[i];
		$('#nuti').val(N);
	} else $('#nuti').val("");
	a = escri + "";
	resn = a.match(regvarn);
	resl = a.match(regvarl);
	if (resl != null) {
		$('#lees').val(resl[0]);
	} else $('#lees').val("");
	N = "";
	if (resn != null) {
		for (i = 0; i < resn.length; i++)
			N = N + resn[i];
		$('#nues').val(N);
	} else $('#nues').val("");
	fino();


}

function clicko() {
	var a = $('#a1').val() + "";
	var b = $('#a2').val() + "";
	getSound(a, b);
}