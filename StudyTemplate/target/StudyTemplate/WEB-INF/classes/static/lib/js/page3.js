var page3Vue = new Vue({/* 뷰 인스턴스 생성*/
	el : '#page3Vue',/*id : page3Vue */
	data : {/*요청 후 받아올 member list data */
		memberList : []
	},
	mounted : function() {/*인스턴스가 마운트 된 직후 생성 */
		this.getMemberList();
	},
	methods : {
		getMemberList : function() {
			
			var self = this;
			var encodedUrl = encodeURI("member/jpa/memberlistVue");
			
			$.ajax({		
				type : "POST",
				url : encodedUrl,
				dataType : "json",
				success : function(data){			
					console.log(data.RESULT_DATA.MEMBERLIST);
					self.memberList = data.RESULT_DATA.MEMBERLIST;
				},
				error : function(){
					
				}	
			});
		}
	}
})