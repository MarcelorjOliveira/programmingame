function dump(obj) {
	var out = '';
	for ( var i in obj) {
		out += i + ": " + obj[i] + "\n";
	}

	alert(out);

	// or, if you wanted to avoid alerts...

	// var pre = document.createElement('pre');
	// pre.innerHTML = out;
	// document.body.appendChild(pre)
}

function openAjax(fileName) {
	if (fileName.includes('.')) {
		$(document).ready(function() {
			$.ajax({
				url : '/main/exercises/openFile',
				type : 'POST',
				data : 'fileName=' + fileName,
				dataType : 'json',
				cache : false,
				success : function(data) {
					// alert(data);
					// alert(JSON.stringify(data));
					// $('#editor').text(data);
					alert('acertei');
				},
				error : function(data) {
					//alert('errei');
					var editor = ace.edit('editor');
					editor.session.setValue(data.responseText);
					var fileNameExposed = fileName.replace(/_/g,'/');
					$('#fileNameTemplate').text(fileNameExposed);
				},
				done : function(data) {
					// alert('haha');
				}
			});
		});
	}
}

function saveAjax() {
	//alert('saveAjax');
	var fileName = sessionStorage.getItem('fileName');

	var editor = ace.edit('editor');
	var content = encodeURIComponent(editor.session.getValue());
	
	//alert(content);

	if (content) {
		$(document).ready(function() {
			$.ajax({
				url : '/main/exercises/saveFile',
				type : 'POST',
				data : 'fileName=' + fileName + '&content=' + content,
				dataType : 'json',
				cache : false,
				success : function(data) {
					alert('salvou');
					// alert(JSON.stringify(data));
					// $('#editor').text(data);
				},
				error : function(data) {
					//alert('passa por aqui');
					// alert(data);
				},
				done : function(data) {
					// alert('haha');
				}
			});
		});
	}
}

function createTree(data) {
	$('#divTree').jstree('destroy');
	$('#divTree').on('changed.jstree', function(e, data) {
		var idSelected = data.selected[0];

		openAjax(idSelected);
		sessionStorage.setItem('fileName', idSelected);
	}).jstree({
		'core' : {
			'data' : [ data ]
		}
	});
}

function directoryAjax(pathPage) {
	var directory = document.getElementById('directory').value;

	$(document).ready(function() {
		$.ajax({
			url : pathPage,
			type : 'GET',
			data : 'directory=' + directory,
			dataType : 'json',
			cache : false,
			processData : false,
			success : function(data) {
				createTree(data);
			},
			error : function(data) {
				$('#divTree').jstree({
					'core' : {
						'data' : [ data ]
					}
				});
			},
			done : function(data) {
			}
		});
	});
}