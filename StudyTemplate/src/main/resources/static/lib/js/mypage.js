var mypageVue = new Vue({/* 뷰 인스턴스 생성*/
	el : '#mypageVue',/*id : page3Vue */
	data : {/*요청 후 받아올 member list data */
		member : []
	},
	mounted : function() {/*인스턴스가 마운트 된 직후 생성 */
	},
	methods : {
		getMyInfo : function() {
			
			var self = this;
			var encodedUrl = encodeURI("member/jpa/member");
			
			$.ajax({		
				type : "GET",
				url : encodedUrl,
				dataType : "json",
				success : function(data){			
					console.log(data.RESULT_DATA.MYINFO);
					self.member = data.RESULT_DATA.MYINFO;
				},
				error : function(){
					
				}	
			});
		}
	}
})