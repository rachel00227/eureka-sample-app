function showResult(url) {
	var resultModal = $('#resultModal');
	resultModal.find('.modal-body').html('');
	
	$.ajax({ 
		type: "GET", 
        url: url,
        dataType: 'json',
        success: function(data){ 
        	htmlData = '<ul>';	
        	if(data.length > 0) {
        		$.each(data, function(key, value){
        			if(value.index == "unknown"){
        				htmlData += '<li><em>'+ value.message +'</em></li>';
        			} else{
        				htmlData += '<li>'+ value.index +': '+ value.message +'</li>';
        			}
        		})
        	}
        	htmlData += '</ul>';
        	resultModal.find('.modal-body').html(htmlData);
        	resultModal.modal('show');
        },
        error: function(request, status, error){
        	console.log("code:"+request.status);
        	resultModal.find('.modal-body').html(request.responseText);
        	resultModal.modal('show');
        }
	});
}
