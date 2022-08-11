var joinVue = new Vue({
	el:"#joinVue",
	data:{
		name:"",
		userId:"",
		pw:"",
		pw_ck:"",
		department:"",
		rank:"",
		position:"",
		isOverlappedId:true,//아이디 중복 확인
		isUserIdKorean:false,//아이디 한글 확인
		underUserIdLength:false,//아이디 길이 확인
		underPwLength:false,//비밀번호 길이 확인
		incorrectPw:false,//비밀번호 일치 확인
		unavailablePw:false,//비밀번호 영문자 숫자 확인
		idWarning:false,
		pwWarning:false,
		pwCkWarning:false
	},
	methods : {
		joinClicked : function(){/*회원가입 확인 버튼 클릭*/
			var self = this;
			if(self.checkNoBlank()) 
				if (self.isOverlappedId==true) alert('아이디 중복을 확인하세요.');
				else{
					if (self.allValidationCheck()) self.createMember();
					else alert('올바른 정보를 입력하세요.');
				}
				
		},
		createMember : function(){/*회원가입*/ 
			var self = this;
			var encodedUrl = encodeURI("member/jdbc/memberJoin");

			$.ajax({		
				type : "POST",
				url : encodedUrl,
				dataType : "json",
				data:{
					"name":self.name,
					"userId":self.userId,
					"pw":self.pw,
					"department":self.department,
					"rank":self.rank,
					"position":self.position
				},
				success : function(data){
					console.log(data.RESULT_MSG);
					alert('등록되었습니다. ['+ data.RESULT_MSG+']');
					window.location.href = self.getContextPath()+"/login";
				},
				error : function(data){
					console.log(data.RESULT_MSG);
					alert('등록에 실패하였습니다.['+ data.RESULT_MSG+']');
				}
			});
			
		},
		checkNoBlank : function(){/*빈칸 체크*/
			var self = this;
			if(self.name==""){
				alert("이름을 입력하세요.");
				//$("#name").focus(); 실행 안됨
				return false;
			}else if(self.userId==""){
				alert("아이디를 입력하세요.");
				//$("#userId").focus();
				return false;
			}else if(self.pw==""){
				alert("비밀번호를 입력하세요.");
				//$("#pw").focus();
				return false;
			}
			return true;
		},
		checkIdCondition : function(){/*아이디 유효성 검사*/
			var self = this;
			const korean = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
			if (korean.test(self.userId)==true){//한글 사용불가
				self.isUserIdKorean=true;
				self.idWarning=true;
			}else{
				self.isUserIdKorean=false;
				self.idWarning=false;
			}
			if (self.userId.length < 8){//길이 미충족(8자 이상)
				self.underUserIdLength=true;
				self.idWarning=true;
			}else{
				self.underUserIdLength=false;
				self.idWarning=false;
			}
		},
		checkPwCondition : function(){/*비밀번호 유효성 검사*/
			var self = this;
			var encodedUrl = encodeURI("member/isPwAvailable");
			
			if (self.pw.length < 8){//길이 미충족(8자 이상)
				self.underPwLength=true;
				self.pwWarning=true;
			}else{
				self.underPwLength=false;
				self.pwWarning=false;
			}
			if (self.pw!=self.pw_ck){//비밀번호 불일치
				self.incorrectPw=true;
				self.pwCkWarning=true;
			}else{
				self.incorrectPw=false;
				self.pwCkWarning=false;
			}

			$.ajax({//영문자, 숫자 모두 포함 확인(Service단에서 구현)
				type : "POST",
				url : encodedUrl,
				dataType : "json",
				data:{
					"pw":self.pw
				},
				success : function(data){
					console.log(data.RESULT_MSG);
					if(data.RESULT_MSG=="unavailable") {
						self.unavailablePw=true;
						self.pwWarning=true;
					}
					else {
						self.unavailablePw=false;
						self.pwWarning=false;
					}
				},
				error : function(data){
					console.log(data.RESULT_MSG);
				}
			});
		},
		checkOverlappedId : function(){/*아이디 중복체크*/ 
			var self = this;
			var encodedUrl = encodeURI("member/jpa/isMemberOverlapped");
			if(!self.isUserIdKorean&&!self.underUserIdLength&&self.userId!=""){
				$.ajax({		
					type : "POST",
					url : encodedUrl,
					dataType : "json",
					data:{
						"userId":self.userId
					},
					success : function(data){
						console.log(data.RESULT_MSG);
						if(data.RESULT_MSG=="unoverlapped") {
							self.isOverlappedId=false;
							alert('사용 가능한 아이디입니다.');
						}
						else {
							self.isOverlappedId=true;
							alert('사용 불가능한 아이디입니다. 다른 아이디를 사용하세요.');
						}
					},
					error : function(data){
						console.log(data.RESULT_MSG);
					}
				});
			}
		},
		allValidationCheck : function(){
			var self = this;
			if ((!self.isUserIdKorea)&&(!self.underUserIdLength)
					&&(!self.underPwLength)&&(!self.incorrectPw)&&(!self.unavailablePw)) return true;
			else return false;
		},
		getContextPath : function(){//${pageContext.request.contextPath}
			var hostIndex = location.href.indexOf( location.host ) + location.host.length;		
			return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
		}
	}
})
