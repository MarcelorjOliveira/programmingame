jQuery.loadScript = function (url, callback) {
    jQuery.ajax({
        url: url,
        dataType: 'script',
        success: callback,
        async: true
    });
}

function dump(obj) {
    var out = '';
    for (var i in obj) {
        out += i + ": " + obj[i] + "\n";
    }

    alert(out);

    // or, if you wanted to avoid alerts...

    //var pre = document.createElement('pre');
    //pre.innerHTML = out;
    //document.body.appendChild(pre)
}

function directoryAjax(pathPage) {
	var directory = document.getElementById('directory').value;
				
		$(document).ready(function(){
			$.ajax({
				url: pathPage,
					type: 'GET', 
					data: 'directory='+directory,
					dataType: 'json', 
					cache: false,
					processData: false,
					success: function(data) { 
						$('#divTree').jstree('destroy');
						$('#divTree').jstree({ 'core' : {
						    'data' : [data]
						} });
					},
					error : function (data) {
						$('#divTree').jstree({ 'core' : {
						    'data' : [data]
						} });
					},
					done : function (data) {
					}
				});  
			
		});
} 