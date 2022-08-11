
/* Page Ready */
$(document).ready(function() {
	getMemberList();
});

/* Get Member List */
function getMemberList() {	
	var encodedUrl = encodeURI("member/jpa/memberlist");	
	/*
	var data = {
			pname : name,
			pcsci : csciname,
			"${_csrf.parameterName}" : "${_csrf.token}"
	}
	 */			
	
	$.ajax({		
		type : "GET",
		url : encodedUrl,
		dataType : "json",		
		//data : data,
		success : function(data){			
			console.log(data.RESULT_DATA.MEMBERLIST);
			$.each(data.RESULT_DATA.MEMBERLIST, function(index, member) {
				$('#member_table').append("<tr><td>" + member.userId + "</td><td>" + member.name + "</td><td>" + member.pw + "</td></tr>");
			});
		},
		error : function(){
			
		}	
	});
}	